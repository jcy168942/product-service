package com.musinsa.domain.brand;

import com.musinsa.api.brand.dto.BrandDto.SaveBrandRequest;
import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandApplicationService {

  private final BrandService brandService;

  @Transactional
  public void createBrand(SaveBrandRequest saveBrandRequest) {
    Brand brand = createBrandFromProductRequest(saveBrandRequest);
    brandService.saveBrand(brand);
  }

  @Transactional
  public void updateBrandName(Long brandId, SaveBrandRequest saveBrandRequest) {
    brandService.updateBrandName(brandId, saveBrandRequest.getBrandName());
  }

  @Transactional
  public void deleteBrand(Long brandId) {
    brandService.deleteBrandById(brandId);
  }

  private Brand createBrandFromProductRequest(SaveBrandRequest saveBrandRequest) {
    return Brand.builder()
        .brandName(saveBrandRequest.getBrandName())
        .build();
  }

}
