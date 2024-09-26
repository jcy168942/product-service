package com.musinsa.domain.price.service;

import com.musinsa.domain.price.entitiy.CategoryHighPrice;
import com.musinsa.domain.price.repository.CategoryHighPriceRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HighPriceService {

  private final CategoryHighPriceRepository categoryHighPriceRepository;

  public Optional<CategoryHighPrice> getHighPriceByCategoryName(String categoryName) {
    return categoryHighPriceRepository.findHighestPriceByCategoryName(categoryName);
  }

}
