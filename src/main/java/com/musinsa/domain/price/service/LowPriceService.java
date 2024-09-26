package com.musinsa.domain.price.service;

import com.musinsa.domain.price.entitiy.CategoryLowPrice;
import com.musinsa.domain.price.repository.CategoryLowPriceRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LowPriceService {

  private final CategoryLowPriceRepository lowPriceRepository;

  public List<Object[]> getLowPrices() {
    return lowPriceRepository.findDistinctLowestPricesByCategory();
  }

  public List<CategoryLowPrice> getLowPricesByBrandAndCategory() {
    return lowPriceRepository.findLowestPricesByBrandAndCategory();
  }

  public Optional<CategoryLowPrice> getLowPriceByCategoryName(String categoryName) {
    return lowPriceRepository.findLowestPriceByCategoryName(categoryName);
  }

}
