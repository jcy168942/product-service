package com.musinsa.api.brand.dto;

import com.musinsa.domain.brand.entitiy.Brand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BrandDto {

  @NoArgsConstructor
  @Builder
  @AllArgsConstructor
  @Getter
  public static class SaveBrandRequest {

    @NotNull(message = "브랜드를 입력해주세요.")
    private String brandName;

  }
}
