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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class PriceAdjustmentServiceTest {

  @Mock
  private CategoryLowPriceRepository categoryLowPriceRepository;

  @Mock
  private CategoryHighPriceRepository categoryHighPriceRepository;

  @InjectMocks
  private PriceAdjustmentService priceAdjustmentService;

  private Product product;
  private Category category;
  private Brand brand;
  private CategoryLowPrice currentLowPrice;
  private CategoryHighPrice currentHighPrice;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Brand 객체 초기화
    brand = Brand.builder()
        .brandId(1L) // ID 설정
        .brandName("Test Brand") // 브랜드 이름 설정
        .build();

    // Category 객체 초기화
    category = Category.builder()
        .categoryId(1L) // ID 설정
        .categoryName("Test Category") // 카테고리 이름 설정
        .build();

    // Product 객체 초기화
    product = Product.builder()
        .productId(1L)
        .productName("Test Product")
        .productPrice(1500L)
        .brand(brand) // 브랜드 설정
        .category(category) // 카테고리 설정
        .build();

    // 현재 최저가 및 최고가 초기화
    currentLowPrice = CategoryLowPrice.builder()
        .lowestPrice(2000L) // 현재 최저가
        .brand(brand) // 브랜드 설정
        .category(category) // 카테고리 설정
        .product(product) // 연관된 제품 설정
        .build();

    currentHighPrice = CategoryHighPrice.builder()
        .highPrice(1000L) // 현재 최고가
        .brand(brand) // 브랜드 설정
        .category(category) // 카테고리 설정
        .product(product) // 연관된 제품 설정
        .build();

    // Mock 설정
    when(categoryLowPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(currentLowPrice));

    when(categoryHighPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.of(currentHighPrice));
  }

  @Test
  @DisplayName("최저가 갱신 테스트 - 새로운 최저가로 갱신")
  void updateLowestPrice() {
    // 상품의 가격이 현재 최저가보다 낮을 경우
    Product newProduct = Product.builder()
        .productId(1L)
        .productName("Updated Product")
        .productPrice(1000L) // 새로운 최저가
        .brand(brand) // 브랜드 설정
        .category(category) // 카테고리 설정
        .build();

    // when
    priceAdjustmentService.updatePriceChangedCategory(newProduct);

    // then
    verify(categoryLowPriceRepository, times(1)).save(currentLowPrice);
    assertThat(currentLowPrice.getLowestPrice()).isEqualTo(1000L); // 최저가가 갱신되었는지 확인
  }

  @Test
  @DisplayName("최고가 갱신 테스트 - 새로운 최고가로 갱신")
  void updateHighestPrice() {
    // 상품의 가격이 현재 최고가보다 높은 경우
    Product newProduct = Product.builder()
        .productId(1L)
        .productName("Updated Product")
        .productPrice(2000L) // 새로운 최고가
        .brand(brand) // 브랜드 설정
        .category(category) // 카테고리 설정
        .build();

    // when
    priceAdjustmentService.updatePriceChangedCategory(newProduct);

    // then
    verify(categoryHighPriceRepository, times(1)).save(currentHighPrice);
    assertThat(currentHighPrice.getHighPrice()).isEqualTo(2000L); // 최고가가 갱신되었는지 확인
  }

  @Test
  @DisplayName("최저가가 없을 경우 새로 추가 테스트")
  void addNewLowestPrice() {
    // Mock 설정: 현재 최저가가 존재하지 않음
    when(categoryLowPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.empty());

    // when
    priceAdjustmentService.updatePriceChangedCategory(product);

    // then
    verify(categoryLowPriceRepository, times(1)).save(any(CategoryLowPrice.class)); // 새 최저가 추가 확인
  }

  @Test
  @DisplayName("최고가가 없을 경우 새로 추가 테스트")
  void addNewHighestPrice() {
    // Mock 설정: 현재 최고가가 존재하지 않음
    when(categoryHighPriceRepository.findByBrandAndCategory(any(), any()))
        .thenReturn(Optional.empty());

    // when
    priceAdjustmentService.updatePriceChangedCategory(product);

    // then
    verify(categoryHighPriceRepository, times(1)).save(any(CategoryHighPrice.class)); // 새 최고가 추가 확인
  }

}