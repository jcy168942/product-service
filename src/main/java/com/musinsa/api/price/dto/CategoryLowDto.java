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
public class CategoryLowDto {

  @JsonProperty("category")
  private String category;
  @JsonProperty("brand")
  private String brand;
  @JsonProperty("price")
  private Long price;

  @NoArgsConstructor
  @Builder
  @Getter
  @AllArgsConstructor
  public static class CategoryLowResponse {

    @JsonProperty("categories")
    private List<CategoryLowDto> categories;
    @JsonProperty("total_price")
    private long totalPrice;

  }

}
