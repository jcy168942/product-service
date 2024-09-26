package com.musinsa.api.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SaveProductRequest {

    @NotNull(message = "브랜드ID가 없습니다.")
    private Long brandId;

    @NotNull(message = "카테고리ID가 없습니다.")
    private Long categoryId;

    @NotNull(message = "상품명을 입력해주세요.")
    private String productName;

    @NotNull(message = "상품가격을 입력해주세요.")
    private Long productPrice;

  }

}
