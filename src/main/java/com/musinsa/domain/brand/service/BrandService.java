package com.musinsa.domain.brand.service;

import static com.musinsa.global.constants.ExceptionConstants.BrandExceptionConstants.CODE_BRAND_NOT_FOUND;
import static com.musinsa.global.constants.ExceptionConstants.BrandExceptionConstants.MESSAGE_BRAND_NOT_FOUND;

import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.brand.repository.BrandRepository;
import com.musinsa.global.exception.MusinsaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandService {

  private final BrandRepository brandRepository;

  public void saveBrand(Brand brand) {
    brandRepository.save(brand);
  }

  public void updateBrandName(Long brandId, String brandName) {
    Brand brand = findByBrandId(brandId);
    brand.updateBrandName(brandName);
    brandRepository.save(brand);
  }

  public void deleteBrandById(Long brandId) {
    Brand brand = findByBrandId(brandId);
    brand.deleteBrand();
    brandRepository.save(brand);
  }

  public Brand findByBrandId(Long brandId) {
    return brandRepository.findByBrandIdAndDeleteYnFalse(brandId)
        .orElseThrow(() -> new MusinsaException(CODE_BRAND_NOT_FOUND, MESSAGE_BRAND_NOT_FOUND));
  }

}
