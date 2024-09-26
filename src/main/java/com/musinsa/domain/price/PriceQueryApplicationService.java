package com.musinsa.domain.price;

import static com.musinsa.global.constants.ExceptionConstants.CategoryExceptionConstants.CODE_CATEGORY_NOT_FOUND;
import static com.musinsa.global.constants.ExceptionConstants.CategoryExceptionConstants.MESSAGE_CATEGORY_NOT_FOUND;
import static com.musinsa.global.constants.ExceptionConstants.ProductExceptionConstants.CODE_PRODUCTS_NOT_EXIST;

import com.musinsa.api.price.dto.BrandCategoryDto;
import com.musinsa.api.price.dto.BrandCategoryDto.BrandLowPriceResponse;
import com.musinsa.api.price.dto.CategoryHighLowDto.BrandHighDto;
import com.musinsa.api.price.dto.CategoryHighLowDto.BrandLowDto;
import com.musinsa.api.price.dto.CategoryHighLowDto.CategoryHighLowResponse;
import com.musinsa.api.price.dto.CategoryLowDto;
import com.musinsa.api.price.dto.CategoryLowDto.CategoryLowResponse;
import com.musinsa.domain.price.entitiy.CategoryLowPrice;
import com.musinsa.domain.price.service.HighPriceService;
import com.musinsa.domain.price.service.LowPriceService;
import com.musinsa.global.exception.MusinsaException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PriceQueryApplicationService {

  private final LowPriceService lowPriceService;
  private final HighPriceService highPriceService;

  //구현 1 카테고리 별 최저가격 브랜드와 상품가격, 총액을 조회하는 API
  public CategoryLowResponse getCategoryLowestPrice() {
    List<Object[]> lowPrices = lowPriceService.getLowPrices();

    // Object[]에서 DTO로 변환
    List<CategoryLowDto> categories = lowPrices.stream()
        .map(result -> new CategoryLowDto(
            (String) result[0],  // category_name
            (String) result[1],  // brand_name
            (Long) result[2]     // product_price
        )).toList();

    // 총액 계산
    long totalPrice = categories.stream().mapToLong(CategoryLowDto::getPrice).sum();

    return CategoryLowResponse.builder()
        .categories(categories)
        .totalPrice(totalPrice)
        .build();
  }

  //구현2 단일 브랜드로 모든 카테고리 상품을 구매할 떄 최저가격에 판매하는 브랜드와 카테고리의 상품가격,총액을 조회하는 API
  public BrandLowPriceResponse getBrandWithLowestTotalPrice() {
    // 모든 브랜드의 카테고리별 최저가 데이터를 조회
    List<CategoryLowPrice> results = lowPriceService.getLowPricesByBrandAndCategory();

    // 브랜드별 카테고리별 최저가 데이터를 그룹화
    Map<String, List<BrandCategoryDto>> brandCategoryMap = results.stream()
        .collect(Collectors.groupingBy(
            bc -> bc.getBrand().getBrandName(),
            Collectors.mapping(
                bc -> new BrandCategoryDto(
                    bc.getCategory().getCategoryName(),
                    bc.getLowestPrice()
                ),
                Collectors.toList()
            )
        ));

    // 각 브랜드별로 카테고리 가격의 총합 계산
    Optional<Entry<String, List<BrandCategoryDto>>> brandWithLowestPrice = brandCategoryMap.entrySet().stream()
        .min(Comparator.comparing(
            entry -> entry.getValue().stream()
                .mapToLong(BrandCategoryDto::getPrice).sum()
        ));

    // 최저가 브랜드 응답 객체로 변환
    if (brandWithLowestPrice.isPresent()) {
      String brandName = brandWithLowestPrice.get().getKey();
      List<BrandCategoryDto> categories = brandWithLowestPrice.get().getValue();
      long totalPrice = categories.stream().mapToLong(BrandCategoryDto::getPrice).sum();

      return BrandLowPriceResponse.builder()
          .brand(brandName)
          .categories(categories)
          .totalPrice(totalPrice)
          .build();
    } else {
      throw new MusinsaException(CODE_PRODUCTS_NOT_EXIST, CODE_PRODUCTS_NOT_EXIST);
    }
  }

  //구현 3 카테고리 이름으로 최저,최고 가격 브랜드와 상품 가격을 조회하는 API
  public CategoryHighLowResponse getLowestAndHighestPriceByCategoryName(String categoryName) {
    // 최저가 조회
    List<BrandLowDto> lowestPrice = lowPriceService.getLowPriceByCategoryName(categoryName)
        .map(bl -> List.of(new BrandLowDto(
            bl.getBrand().getBrandName(),
            bl.getLowestPrice()
        )))
        .orElseThrow(() -> new MusinsaException(CODE_CATEGORY_NOT_FOUND, MESSAGE_CATEGORY_NOT_FOUND));

    // 최고가 조회
    List<BrandHighDto> highestPrice = highPriceService.getHighPriceByCategoryName(categoryName)
        .map(bh -> List.of(new BrandHighDto(
            bh.getBrand().getBrandName(),
            bh.getHighPrice()
        )))
        .orElseThrow(() -> new MusinsaException(CODE_CATEGORY_NOT_FOUND, MESSAGE_CATEGORY_NOT_FOUND));

    return CategoryHighLowResponse.builder()
        .categoryName(categoryName)
        .lowestPrice(lowestPrice)
        .highestPrice(highestPrice)
        .build();
  }

}
