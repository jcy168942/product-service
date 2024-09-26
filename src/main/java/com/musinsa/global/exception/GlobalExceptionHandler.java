package com.musinsa.global.exception;

import static com.musinsa.global.constants.ExceptionConstants.ApplicationExceptionConstants.CODE_APPLICATION_UNKNOWN;
import static com.musinsa.global.constants.ExceptionConstants.ApplicationExceptionConstants.MESSAGE_APPLICATION_UNKNOWN;
import static com.musinsa.global.constants.ExceptionConstants.BrandExceptionConstants.CODE_ALREADY_BRAND;
import static com.musinsa.global.constants.ExceptionConstants.BrandExceptionConstants.MESSAGE_ALREADY_BRAND;
import static com.musinsa.global.constants.ExceptionConstants.ProductExceptionConstants.CODE_ALREADY_PRODUCT;
import static com.musinsa.global.constants.ExceptionConstants.ProductExceptionConstants.MESSAGE_ALREADY_PRODUCT;

import com.musinsa.global.response.ApiResponse;
import java.util.stream.Collectors;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = { MusinsaException.class })
  public ApiResponse handleCustomException(MusinsaException ex) {
    return ApiResponse.fail(ex.getCode(), ex.getMessage(), null);
  }

  @ExceptionHandler(value = { Exception.class })
  public ApiResponse handleException(Exception ex) {
    return ApiResponse.fail("INTERNAL_SERVER_ERROR", ex.getMessage(), null);
  }

  @ExceptionHandler(value = { BindException.class })
  public ApiResponse handleBindException(BindException ex) {
    return ApiResponse.fail("BIND_EXCEPTION_ERROR", ex.getMessage(), null);
  }

  // 유효성 검증 실패 처리
  @ExceptionHandler(value = { MethodArgumentNotValidException.class })
  public ApiResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return ApiResponse.fail("VALIDATION_ERROR", errors, null);
  }

  @ExceptionHandler(value = { DataIntegrityViolationException.class })
  public ApiResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    String message = ex.getMessage();

    // 브랜드 중복일 경우
    if (message.contains("UK_BRAND")) {
      return ApiResponse.fail(
          CODE_ALREADY_BRAND,
          MESSAGE_ALREADY_BRAND,
          null
      );
    }

    // 카테고리 중복일 경우
    if (message.contains("UK_CATEGORY")) {
      return ApiResponse.fail(
          CODE_ALREADY_BRAND,
          MESSAGE_ALREADY_BRAND,
          null
      );
    }

    // 상품 중복일 경우
    if (message.contains("UK_PRODUCT")) {
      return ApiResponse.fail(
          CODE_ALREADY_PRODUCT,
          MESSAGE_ALREADY_PRODUCT,
          null
      );
    }

    // 기타 예외 처리
    return ApiResponse.fail(CODE_APPLICATION_UNKNOWN, MESSAGE_APPLICATION_UNKNOWN, null);
  }

}
