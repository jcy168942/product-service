package com.musinsa.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

  private String code;
  private String message;
  private T data;

  private static final String SUCCESS_CODE = "SUCCESS";

  public static <T> ApiResponse success(T data) {
    return ApiResponse.builder()
        .code(SUCCESS_CODE)
        .message(null)
        .data(data)
        .build();
  }

  public static <T> ApiResponse fail(String code, String message, T data) {
    ApiResponse.ApiResponseBuilder<T> responseBuilder = ApiResponse.<T>builder()
        .code(code)
        .message(message);

    if (data != null) {
      responseBuilder.data(data);
    }

    return responseBuilder.build();
  }

}
