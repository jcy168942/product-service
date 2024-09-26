package com.musinsa.domain.price.repository;

import com.musinsa.domain.price.entitiy.CategoryHighPrice;
import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.category.entitiy.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryHighPriceRepository extends JpaRepository<CategoryHighPrice, Long> {

  Optional<CategoryHighPrice> findByBrandAndCategory(Brand brand, Category category);
  void deleteByBrandAndCategory(Brand brand, Category category);

  @Query(value = "SELECT chp.* " +
      "FROM category_highest_price chp " +
      "JOIN category c ON chp.category_id = c.category_id " +
      "JOIN brand b ON chp.brand_id = b.brand_id " +
      "WHERE chp.high_price = (" +
      "    SELECT MAX(chp2.high_price) " +
      "    FROM category_highest_price chp2 " +
      "    JOIN category c2 ON chp2.category_id = c2.category_id " +
      "    WHERE c2.category_name = :categoryName " +
      ") " +
      "AND b.delete_yn = false " +
      "AND c.delete_yn = false",
      nativeQuery = true)
  Optional<CategoryHighPrice> findHighestPriceByCategoryName(@Param("categoryName") String categoryName);

}
