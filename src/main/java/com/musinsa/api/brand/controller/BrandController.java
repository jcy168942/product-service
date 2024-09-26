package com.musinsa.api.brand.controller;

import com.musinsa.api.brand.dto.BrandDto.SaveBrandRequest;
import com.musinsa.domain.brand.BrandApplicationService;
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

@Tag(name = "브랜드 API", description = "브랜드 CRUD 관련 API 입니다.")
@RequiredArgsConstructor
@RequestMapping("/v1/brand")
@RestController
public class BrandController {

  private final BrandApplicationService brandApplicationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "브랜드 생성", description = "새로운 브랜드를 생성합니다.")
  public ApiResponse createBrand(@Valid @RequestBody SaveBrandRequest saveBrandRequest) {
    brandApplicationService.createBrand(saveBrandRequest);
    return ApiResponse.success(null);
  }

  @PatchMapping("/{brandId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "브랜드 수정", description = "브랜드를 수정 합니다.")
  public ApiResponse updateBrand(@PathVariable Long brandId, @RequestBody SaveBrandRequest saveBrandRequest) {
    brandApplicationService.updateBrandName(brandId, saveBrandRequest);
    return ApiResponse.success(null);
  }

  @DeleteMapping("/{brandId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "브랜드 삭제", description = "브랜드를 삭제합니다.")
  public ApiResponse deleteBrand(@PathVariable Long brandId) {
    brandApplicationService.deleteBrand(brandId);
    return ApiResponse.success(null);
  }

}
