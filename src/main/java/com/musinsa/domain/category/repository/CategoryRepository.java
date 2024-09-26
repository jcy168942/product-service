package com.musinsa.domain.category.repository;

import com.musinsa.domain.category.entitiy.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
