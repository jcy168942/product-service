package com.musinsa.api.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CategoryHighLowDto {

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BrandLowDto {

    @JsonProperty("brand")
    private String brandName;
    @JsonProperty("price")
    private Long price;

  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class BrandHighDto {

    @JsonProperty("brand")
    private String brandName;
    @JsonProperty("price")
    private Long price;

  }

  @NoArgsConstructor
  @Builder
  @Getter
  @AllArgsConstructor
  public static class CategoryHighLowResponse {

    @JsonProperty("category")
    private String categoryName;
    @JsonProperty("low")
    private List<BrandLowDto> lowestPrice;
    @JsonProperty("high")
    private List<BrandHighDto> highestPrice;

  }

}
