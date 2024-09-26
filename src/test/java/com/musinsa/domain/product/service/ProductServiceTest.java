package com.musinsa.domain.product.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.musinsa.domain.product.entitiy.Product;
import com.musinsa.domain.product.repository.ProductRepository;
import com.musinsa.global.exception.MusinsaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductService productService;

  private Product existingProduct;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Product 객체 초기화 (빌더 패턴 사용)
    existingProduct = Product.builder()
        .productId(1L)  // ID 설정
        .productName("기존 상품")  // 이름 설정
        .productPrice(1000L)  // 가격 설정
        .deleteYn(false)  // 삭제 상태 설정
        .build();

    when(productRepository.findByProductIdAndDeleteYnFalse(existingProduct.getProductId())).thenReturn(Optional.of(existingProduct));
  }

  @Test
  @DisplayName("새로운 상품 생성 테스트")
  void createProduct() {
    // given
    Product newProduct = Product.builder()
        .productName("나이키 상의")  // 이름 설정
        .productPrice(2000L)  // 가격 설정
        .deleteYn(false)  // 삭제 상태 기본값
        .build();

    when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // when
    Product createdProduct = productService.createProduct(newProduct);

    // then
    verify(productRepository, times(1)).save(any(Product.class));
    assertThat(createdProduct.getProductName()).isEqualTo("나이키 상의");
  }

  @Test
  @DisplayName("상품 업데이트 테스트")
  void updateProduct() {
    // given
    Product newProductData = Product.builder()
        .brand(existingProduct.getBrand())
        .category(existingProduct.getCategory())
        .productName("업데이트된 상품")
        .productPrice(1500L)
        .build();

    // when
    productService.updateProduct(existingProduct.getProductId(), newProductData);

    // then
    verify(productRepository, times(1)).findByProductIdAndDeleteYnFalse(existingProduct.getProductId());
    // save 메서드가 호출될 때 전달된 Product 객체의 상태를 검증
    ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
    verify(productRepository, times(1)).save(productCaptor.capture());

    // 실제 저장된 Product 객체를 가져와서 검증
    Product savedProduct = productCaptor.getValue();
    assertThat(savedProduct).isNotNull();
    assertThat(savedProduct.getProductName()).isEqualTo("업데이트된 상품");
    assertThat(savedProduct.getProductPrice()).isEqualTo(1500L);
  }

  @Test
  @DisplayName("상품 삭제 테스트")
  void deleteProduct() {
    // when
    Product deletedProduct = productService.deleteProduct(existingProduct.getProductId());

    // then
    verify(productRepository, times(1)).findByProductIdAndDeleteYnFalse(existingProduct.getProductId());
    verify(productRepository, times(1)).save(existingProduct);
    assertThat(deletedProduct.getDeleteYn()).isTrue();  // 삭제 상태 확인
  }

  @Test
  @DisplayName("상품 조회 시 예외 발생 테스트")
  void findProductByIdNotFound() {
    Long productId = 999L; // 존재하지 않는 ID
    when(productRepository.findByProductIdAndDeleteYnFalse(productId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> productService.findProductById(productId))
        .isInstanceOf(MusinsaException.class)
        .hasMessageContaining("상품을 찾을 수 없습니다");
  }

}