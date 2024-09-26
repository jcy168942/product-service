package com.musinsa.domain.brand.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.brand.repository.BrandRepository;
import com.musinsa.global.exception.MusinsaException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BrandServiceTest {

  @Mock
  private BrandRepository brandRepository;

  @InjectMocks
  private BrandService brandService;

  private Brand existingBrand;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Brand 객체 초기화 (빌더 패턴 사용)
    existingBrand = Brand.builder()
        .brandId(1L)  // ID 설정
        .brandName("Nike")  // 이름 설정
        .deleteYn(false)  // 삭제 상태 설정
        .build();

    when(brandRepository.findByBrandIdAndDeleteYnFalse(existingBrand.getBrandId())).thenReturn(Optional.of(existingBrand));
  }

  @Test
  @DisplayName("새로운 브랜드 생성 테스트")
  void saveBrand() {
    // given
    Brand newBrand = Brand.builder()
        .brandName("Asus")  // 이름 설정
        .deleteYn(false)  // 삭제 상태 기본값
        .build();

    when(brandRepository.save(any(Brand.class))).thenAnswer(invocation -> invocation.getArgument(0));

    // when
    brandService.saveBrand(newBrand);

    // then
    verify(brandRepository, times(1)).save(any(Brand.class));
  }

  @Test
  @DisplayName("브랜드 이름 업데이트 테스트")
  void updateBrandName() {
    // given
    String newBrandName = "NB";

    // when
    brandService.updateBrandName(existingBrand.getBrandId(), newBrandName);

    // then
    verify(brandRepository, times(1)).findByBrandIdAndDeleteYnFalse(existingBrand.getBrandId());
    verify(brandRepository, times(1)).save(existingBrand);
    assertThat(existingBrand.getBrandName()).isEqualTo(newBrandName);
  }

  @Test
  @DisplayName("브랜드 삭제 테스트")
  void deleteBrandById() {
    // when
    brandService.deleteBrandById(existingBrand.getBrandId());

    // then
    verify(brandRepository, times(1)).findByBrandIdAndDeleteYnFalse(existingBrand.getBrandId());
    verify(brandRepository, times(1)).save(existingBrand);
    assertThat(existingBrand.getDeleteYn()).isTrue();  // 삭제 상태 확인
  }

  @Test
  @DisplayName("브랜드 조회 시 예외 발생 테스트")
  void findByBrandIdNotFound() {
    Long brandId = 999L; // 존재하지 않는 ID
    when(brandRepository.findByBrandIdAndDeleteYnFalse(brandId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> brandService.findByBrandId(brandId))
        .isInstanceOf(MusinsaException.class)
        .hasMessageContaining("브랜드를 찾을 수 없습니다");
  }

}