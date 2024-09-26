package com.musinsa.api.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandCategoryDto {

  @JsonProperty("category")
  private String categoryName;
  @JsonProperty("price")
  private Long price;

  @NoArgsConstructor
  @Builder
  @Getter
  @AllArgsConstructor
  public static class BrandLowPriceResponse {

    @JsonProperty("brand")
    private String brand;
    @JsonProperty("categories")
    private List<BrandCategoryDto> categories;
    @JsonProperty("total_price")
    private Long totalPrice;
  }

}
