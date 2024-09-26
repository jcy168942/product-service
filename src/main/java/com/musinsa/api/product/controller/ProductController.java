package com.musinsa.api.product.controller;

import com.musinsa.api.product.dto.ProductDto.SaveProductRequest;
import com.musinsa.domain.product.ProductApplicationService;
import com.musinsa.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품 API")
@RequiredArgsConstructor
@RequestMapping("/v1/product")
@RestController
public class ProductController {

  private final ProductApplicationService productApplicationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "상품 생성", description = "새로운 상품을 생성합니다.")
  public ApiResponse createProduct(@Valid @RequestBody SaveProductRequest saveProductRequest) {
    productApplicationService.createProduct(saveProductRequest);
    return ApiResponse.success(null);
  }

  @PatchMapping("/{productId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "상품 수정", description = "상품을 수정합니다.")
  public ApiResponse updateProduct(@PathVariable Long productId, @RequestBody SaveProductRequest saveProductRequest) {
    productApplicationService.updateProduct(productId, saveProductRequest);
    return ApiResponse.success(null);
  }

  @DeleteMapping("/{productId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "상품 삭제", description = "상품을 삭제합니다.")
  public ApiResponse deleteProduct(@PathVariable Long productId) {
    productApplicationService.deleteProduct(productId);
    return ApiResponse.success(null);
  }

}
