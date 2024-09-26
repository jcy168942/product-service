package com.musinsa.domain.price.service;

import com.musinsa.domain.price.entitiy.CategoryHighPrice;
import com.musinsa.domain.price.entitiy.CategoryLowPrice;
import com.musinsa.domain.price.repository.CategoryHighPriceRepository;
import com.musinsa.domain.price.repository.CategoryLowPriceRepository;
import com.musinsa.domain.product.entitiy.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceAdjustmentService {

  private final CategoryLowPriceRepository categoryLowPriceRepository;
  private final CategoryHighPriceRepository categoryHighPriceRepository;

  /**
   * 가격 비교 로직
   * 카테고리별 최저가 상품이 변경이벤트의 상품보다 작을경우 카테고리별 최저가 테이블 변경
   *  true -> 최저가일경우 최고가는 비교할 필요가 없음
   *  false -> 최저가가 아니기 때문에 최고가와 비교
   *        -> 최고가 보다 가격이 높을경우 최고가 테이블 변경
   *
   *  return true -> 캐시 갱신처리
   *         false -> 캐시 갱신처리 X
   *
   *  => 개별적으로 저장하는 로직으로 변경, void 리턴으로 변경
   *     최고가, 또는 최저가 상품이 수정됬을때는
   *     다시 갱신이 필요함
   *     (구현 시간이 부족하여 캐시 부분 적용하지 못했습니다.)
   */
  // 상품 저장 시 가격 정보 업데이트 (최저가/최고가 모두 처리)
  public void updatePriceChangedCategory(Product product) {
    // 최저가 비교 및 갱신
    updateLowestPrice(product);

    // 최고가 비교 및 갱신
    updateHighestPrice(product);
  }

  private void updateLowestPrice(Product product) {
    // 현재 브랜드와 카테고리로 최저가를 확인하고, 존재하면 갱신, 없으면 추가
    categoryLowPriceRepository.findByBrandAndCategory(product.getBrand(), product.getCategory())
        .ifPresentOrElse(
            currentLowPrice -> {
              // 현재 최저가보다 낮으면 갱신
              if (product.isLowerThan(currentLowPrice.getLowestPrice())) {
                currentLowPrice.updateProduct(product);
                currentLowPrice.updateLowestPrice(product.getProductPrice());
                categoryLowPriceRepository.save(currentLowPrice);
              }
            },
            // 최저가가 없을 경우 새로 추가
            () -> {
              CategoryLowPrice categoryLowPrice = createLowPriceByProduct(product);
              categoryLowPriceRepository.save(categoryLowPrice);
            }
        );
  }

  private void updateHighestPrice(Product product) {
    // 현재 브랜드와 카테고리로 최고가를 확인하고, 존재하면 갱신, 없으면 추가
    categoryHighPriceRepository.findByBrandAndCategory(product.getBrand(), product.getCategory())
        .ifPresentOrElse(
            currentHighPrice -> {
              // 현재 최고가보다 높으면 갱신
              if (product.isHigherThan(currentHighPrice.getHighPrice())) {
                currentHighPrice.updateProduct(product);
                currentHighPrice.updateHighPrice(product.getProductPrice());
                categoryHighPriceRepository.save(currentHighPrice);
              }
            },
            // 최고가가 없을 경우 새로 추가
            () -> {
              CategoryHighPrice categoryHighPrice = createHighPriceByProduct(product);
              categoryHighPriceRepository.save(categoryHighPrice);
            }
        );
  }

  public CategoryHighPrice createHighPriceByProduct(Product product) {
    return CategoryHighPrice.builder()
        .brand(product.getBrand())
        .category(product.getCategory())
        .product(product)
        .highPrice(product.getProductPrice())
        .build();
  }

  public CategoryLowPrice createLowPriceByProduct(Product product) {
    return CategoryLowPrice.builder()
        .brand(product.getBrand())
        .category(product.getCategory())
        .product(product)
        .lowestPrice(product.getProductPrice())
        .build();
  }

}
