package com.musinsa.domain.category.service;

import static com.musinsa.global.constants.ExceptionConstants.CategoryExceptionConstants.CODE_CATEGORY_NOT_FOUND;
import static com.musinsa.global.constants.ExceptionConstants.CategoryExceptionConstants.MESSAGE_CATEGORY_NOT_FOUND;

import com.musinsa.domain.category.entitiy.Category;
import com.musinsa.domain.category.repository.CategoryRepository;
import com.musinsa.global.exception.MusinsaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public Category findByCategoryId(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new MusinsaException(CODE_CATEGORY_NOT_FOUND, MESSAGE_CATEGORY_NOT_FOUND));
  }

}
