package com.musinsa.domain.price.service;

import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.category.entitiy.Category;
import com.musinsa.domain.price.entitiy.CategoryHighPrice;
import com.musinsa.domain.price.entitiy.CategoryLowPrice;
import com.musinsa.domain.price.repository.CategoryHighPriceRepository;
import com.musinsa.domain.price.repository.CategoryLowPriceRepository;
import com.musinsa.domain.product.entitiy.Product;
import com.musinsa.domain.product.repository.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PriceRefreshService {

  private final CategoryLowPriceRepository categoryLowPriceRepository;
  private final CategoryHighPriceRepository categoryHighPriceRepository;
  private final ProductRepository productRepository;

  // 특정 브랜드와 관련된 최고가/최저가 데이터 삭제
  public void deletePricesByBrand(Long brandId) {
    // 브랜드와 관련된 최저가 데이터 삭제
    categoryLowPriceRepository.deleteById(brandId);

    // 브랜드와 관련된 최고가 데이터 삭제
    categoryHighPriceRepository.deleteById(brandId);
  }

  // 특정 브랜드와 카테고리에서 가격을 재계산
  public void recalculateCategoryPrices(Product deletedProduct) {
    Brand brand = deletedProduct.getBrand();
    Category category = deletedProduct.getCategory();


    // 삭제된 상품이 현재 최저가 상품인지 확인
    Optional<CategoryLowPrice> currentLowPriceOpt = categoryLowPriceRepository.findByBrandAndCategory(brand, category);
    if (currentLowPriceOpt.isPresent() && currentLowPriceOpt.get().getProduct().equals(deletedProduct)) {
      // 새로운 최저가 상품 조회
      Optional<Product> newLowestProduct = productRepository.findLowestPriceByBrandAndCategory(brand, category);
      if (newLowestProduct.isPresent()) { // 최저가 상품이 있으면 최저가 테이블 갱신
        CategoryLowPrice currentLowPrice = currentLowPriceOpt.get();
        currentLowPrice.updateLowestPrice(newLowestProduct.get().getProductPrice());
        currentLowPrice.updateProduct(newLowestProduct.get());
        categoryLowPriceRepository.save(currentLowPrice);
      } else {
        // 최저가 상품이 없으면 삭제
        categoryLowPriceRepository.deleteByBrandAndCategory(brand, category);
      }
    }

    // 삭제된 상품이 현재 최고가 상품인지 확인
    Optional<CategoryHighPrice> currentHighPriceOpt = categoryHighPriceRepository.findByBrandAndCategory(brand, category);
    if (currentHighPriceOpt.isPresent() && currentHighPriceOpt.get().getProduct().equals(deletedProduct)) {
      // 새로운 최고가 상품 조회
      Optional<Product> newHighestProduct = productRepository.findHighestPriceByBrandAndCategory(brand, category);
      if (newHighestProduct.isPresent()) { // 최고가 상품이 있으면 최고가 테이블 갱신
        CategoryHighPrice currentHighPrice = currentHighPriceOpt.get();
        currentHighPrice.updateHighPrice(newHighestProduct.get().getProductPrice());
        currentHighPrice.updateProduct(newHighestProduct.get());
        categoryHighPriceRepository.save(currentHighPrice);
      } else {
        // 최고가 상품이 없으면 삭제
        categoryHighPriceRepository.deleteByBrandAndCategory(brand, category);
      }
    }
  }

}
