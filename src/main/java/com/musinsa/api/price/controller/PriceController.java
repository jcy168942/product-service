package com.musinsa.api.price.controller;

import com.musinsa.api.price.dto.BrandCategoryDto.BrandLowPriceResponse;
import com.musinsa.api.price.dto.CategoryHighLowDto.CategoryHighLowResponse;
import com.musinsa.api.price.dto.CategoryLowDto.CategoryLowResponse;
import com.musinsa.domain.price.PriceQueryApplicationService;
import com.musinsa.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카테고리, 브랜드 가격조회 API", description = "카테고리, 브랜드별 최저가 최고가 데이터를 조회합니다.")
@RequiredArgsConstructor
@RequestMapping("/v1/price")
@RestController
public class PriceController {

  private final PriceQueryApplicationService priceQueryApplicationService;

  @GetMapping("/lowest-prices")
  @Operation(summary = "카테고리 별 최저가격 브랜드와 상품 가격, 총액 조회 API", description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회 합니다.")
  public ApiResponse<CategoryLowResponse> getLowestPrices() {
    return ApiResponse.success(priceQueryApplicationService.getCategoryLowestPrice());
  }

  @GetMapping("/lowest-total-price")
  @Operation(summary = "브랜드와 카테고리의 상품가격, 총액 조회 API", description = "브랜드별 카테고리의 최저가 가격의 합이 가장 저가인 브랜드를 조회합니다.")
  public ApiResponse<BrandLowPriceResponse> getBrandWithLowestTotalPrice() {
    return  ApiResponse.success(priceQueryApplicationService.getBrandWithLowestTotalPrice());
  }

  @GetMapping("/lowest-highest-price")
  @Operation(summary = "카테고리 이름으로 최저,최고 가격의 브랜드와 상품 가격 조회 API", description = "카테고리 이름으로 조회했을때 최저,최고 가격 브랜드와 상품 가격을 조회합니다.")
  public ApiResponse<CategoryHighLowResponse> getLowestAndHighestPriceByCategoryName(@RequestParam String categoryName) {
    return ApiResponse.success(priceQueryApplicationService.getLowestAndHighestPriceByCategoryName(categoryName));
  }

}
