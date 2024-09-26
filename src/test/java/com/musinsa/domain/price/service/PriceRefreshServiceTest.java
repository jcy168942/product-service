package com.musinsa.domain.price.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.category.entitiy.Category;
import com.musinsa.domain.price.entitiy.CategoryHighPrice;
import com.musinsa.domain.price.entitiy.CategoryLowPrice;
import com.musinsa.domain.price.repository.CategoryHighPriceRepository;
import com.musinsa.domain.price.repository.CategoryLowPriceRepository;
import com.musinsa.domain.product.entitiy.Product;
import com.musinsa.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class PriceRefreshServiceTest {

  @Mock
  private CategoryLowPriceRepository categoryLowPriceRepository;

  @Mock
  private CategoryHighPriceRepository categoryHighPriceRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private PriceRefreshService priceRefreshService;

  private Brand brand;
  private Category category;
  private Product deletedProduct;
  private CategoryLowPrice currentLowPrice;
  private CategoryHighPrice currentHighPrice;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Brand 및 Category 객체 초기화
    brand = Brand.builder()
        .brandId(1L)
        .brandName("Test Brand")
        .build();

    category = Category.builder()
        .categoryId(1L)
        .categoryName("Test Category")
        .build();

    // Deleted Product 객체 초기화
    deletedProduct = Product.builder()
        .productId(1L)
        .productName("Deleted Product")
        .productPrice(1500L)
        .brand(brand) // 브랜드 설정
        .category(category) // 카테고리 설정
        .build();

    // 현재 최저가 및 최고가 객체 초기화
    currentLowPrice = CategoryLowPrice.builder()
        .lowestPrice(2000L)
        .brand(brand)
        .category(category)
        .product(deletedProduct)
        .build();

    currentHighPrice = CategoryHighPrice.builder()
        .highPrice(3000L)
        .brand(brand)
        .category(category)
        .product(deletedProduct)
        .build();
  }

  @Test
  @DisplayName("브랜드에 따른 가격 데이터 삭제 테스트")
  void deletePricesByBrand() {
    // when
    priceRefreshService.deletePricesByBrand(brand.getBrandId());

    // then
    verify(categoryLowPriceRepository, times(1)).deleteById(brand.getBrandId());
    verify(categoryHighPriceRepository, times(1)).deleteById(brand.getBrandId());
  }

  @Test
  @DisplayName("카테고리 가격 재계산 - 최저가 갱신")
  void recalculateCategoryPrices_UpdateLowestPrice() {
    // Mock 설정: 현재 최저가가 존재
    when(categoryLowPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(currentLowPrice));

    // 새로운 최저가 상품을 Mock 설정
    Product newLowestProduct = Product.builder()
        .productId(2L)
        .productName("New Lowest Product")
        .productPrice(1000L)
        .brand(brand)
        .category(category)
        .build();

    when(productRepository.findLowestPriceByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(newLowestProduct));

    // when
    priceRefreshService.recalculateCategoryPrices(deletedProduct);

    // then
    verify(categoryLowPriceRepository, times(1)).save(currentLowPrice);
    assertThat(currentLowPrice.getLowestPrice()).isEqualTo(1000L); // 최저가가 갱신되었는지 확인
    assertThat(currentLowPrice.getProduct()).isEqualTo(newLowestProduct); // 새 제품으로 갱신 확인
  }

  @Test
  @DisplayName("카테고리 가격 재계산 - 최저가 삭제")
  void recalculateCategoryPrices_DeleteLowestPrice() {
    // Mock 설정: 현재 최저가가 존재
    when(categoryLowPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(currentLowPrice));

    // Mock 설정: 새로운 최저가가 존재하지 않음
    when(productRepository.findLowestPriceByBrandAndCategory(any(), any()))
        .thenReturn(Optional.empty());

    // when
    priceRefreshService.recalculateCategoryPrices(deletedProduct);

    // then
    verify(categoryLowPriceRepository, times(1)).deleteByBrandAndCategory(brand, category); // 최저가 삭제 확인
  }

  @Test
  @DisplayName("카테고리 가격 재계산 - 최고가 갱신")
  void recalculateCategoryPrices_UpdateHighestPrice() {
    // Mock 설정: 현재 최고가가 존재
    when(categoryHighPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(currentHighPrice));

    // 새로운 최고가 상품을 Mock 설정
    Product newHighestProduct = Product.builder()
        .productId(3L)
        .productName("New Highest Product")
        .productPrice(4000L)
        .brand(brand)
        .category(category)
        .build();

    when(productRepository.findHighestPriceByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(newHighestProduct));

    // when
    priceRefreshService.recalculateCategoryPrices(deletedProduct);

    // then
    verify(categoryHighPriceRepository, times(1)).save(currentHighPrice);
    assertThat(currentHighPrice.getHighPrice()).isEqualTo(4000L); // 최고가가 갱신되었는지 확인
    assertThat(currentHighPrice.getProduct()).isEqualTo(newHighestProduct); // 새 제품으로 갱신 확인
  }

  @Test
  @DisplayName("카테고리 가격 재계산 - 최고가 삭제")
  void recalculateCategoryPrices_DeleteHighestPrice() {
    // Mock 설정: 현재 최고가가 존재
    when(categoryHighPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(currentHighPrice));

    // Mock 설정: 새로운 최고가가 존재하지 않음
    when(productRepository.findHighestPriceByBrandAndCategory(any(), any()))
        .thenReturn(Optional.empty());

    // when
    priceRefreshService.recalculateCategoryPrices(deletedProduct);

    // then
    verify(categoryHighPriceRepository, times(1)).deleteByBrandAndCategory(brand, category); // 최고가 삭제 확인
  }

}