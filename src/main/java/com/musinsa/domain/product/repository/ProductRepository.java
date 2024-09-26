package com.musinsa.domain.product.repository;

import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.category.entitiy.Category;
import com.musinsa.domain.product.entitiy.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByProductIdAndDeleteYnFalse(Long productId);

  @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.category = :category AND p.deleteYn = false ORDER BY p.productPrice ASC LIMIT 1")
  Optional<Product> findLowestPriceByBrandAndCategory(@Param("brand") Brand brand, @Param("category") Category category);

  @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.category = :category AND p.deleteYn = false ORDER BY p.productPrice DESC LIMIT 1")
  Optional<Product> findHighestPriceByBrandAndCategory(@Param("brand") Brand brand, @Param("category") Category category);

}
