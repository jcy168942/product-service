package com.musinsa.global.aop;

import com.musinsa.domain.price.service.PriceAdjustmentService;
import com.musinsa.domain.price.service.PriceRefreshService;
import com.musinsa.domain.product.entitiy.Product;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PriceCalculationAspect {

  private final PriceAdjustmentService priceAdjustmentService;
  private final PriceRefreshService priceRefreshService;

  // 상품 생성 및 업데이트 후 가격 업데이트 처리
  @AfterReturning(pointcut = "execution(* com.musinsa.domain.product.ProductApplicationService.createProduct(..)) || " +
      "execution(* com.musinsa.domain.product.ProductApplicationService.updateProduct(..))",
      returning = "product")
  public void updatePriceAfterProductChange(Product product) {
    // 생성 또는 업데이트된 Product 객체를 받아서 가격 변경 로직 실행
    if (product != null) {
      priceAdjustmentService.updatePriceChangedCategory(product);
    }
  }

  // 상품 삭제 후 카테고리별 가격 재계산
  @AfterReturning(pointcut = "execution(* com.musinsa.domain.product.ProductApplicationService.deleteProduct(..))",
      returning = "deletedProduct")
  public void recalculatePriceAfterProductDeletion(Product deletedProduct) {
    if (deletedProduct != null) {
      priceRefreshService.recalculateCategoryPrices(deletedProduct);
    }
  }

  // 브랜드 삭제 후 연관된 최고가/최저가 데이터만 삭제
  @After("execution(* com.musinsa.domain.brand.BrandApplicationService.deleteBrand(..))")
  public void deletePriceDataAfterBrandDeletion(JoinPoint joinPoint) {
    Long brandId = (Long) joinPoint.getArgs()[0];
    priceRefreshService.deletePricesByBrand(brandId);
  }

}
