package com.musinsa.domain.price.repository;

import com.musinsa.domain.price.entitiy.CategoryLowPrice;
import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.category.entitiy.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryLowPriceRepository extends JpaRepository<CategoryLowPrice, Long> {

  Optional<CategoryLowPrice> findByBrandAndCategory(Brand brand, Category category);
  void deleteByBrandAndCategory(Brand brand, Category category);

  @Query(value = "SELECT c.CATEGORY_NAME AS category_name, " +
      "b.BRAND_NAME AS brand_name, " +
      "clp.LOWEST_PRICE AS product_price " +
      "FROM CATEGORY_LOWEST_PRICE clp " +
      "JOIN CATEGORY c ON clp.CATEGORY_ID = c.CATEGORY_ID " +
      "JOIN BRAND b ON clp.BRAND_ID = b.BRAND_ID " +
      "WHERE clp.LOWEST_PRICE = ( " +
      "    SELECT MIN(clp2.LOWEST_PRICE) " +
      "    FROM CATEGORY_LOWEST_PRICE clp2 " +
      "    WHERE clp2.CATEGORY_ID = clp.CATEGORY_ID " +
      ") " +
      "AND c.DELETE_YN = false " +
      "AND b.DELETE_YN = false " +
      "GROUP BY c.CATEGORY_ID, c.CATEGORY_NAME, b.BRAND_NAME, clp.LOWEST_PRICE",
      nativeQuery = true)
  List<Object[]> findDistinctLowestPricesByCategory();

  @Query("SELECT clp FROM CategoryLowPrice clp " +
      "JOIN clp.brand b " +
      "JOIN clp.category c " +
      "WHERE b.brandId = (" +
      "   SELECT b_sub.brandId FROM CategoryLowPrice clp_sub " +
      "   JOIN clp_sub.brand b_sub " +
      "   JOIN clp_sub.category c_sub " +
      "   WHERE b_sub.deleteYn = false " +
      "     AND c_sub.deleteYn = false " +
      "   GROUP BY b_sub.brandId " +
      "   ORDER BY SUM(clp_sub.lowestPrice) ASC " +
      "   LIMIT 1" +
      ") " +
      "AND b.deleteYn = false " +
      "AND c.deleteYn = false")
  List<CategoryLowPrice> findLowestPricesByBrandAndCategory();

  @Query(value = "SELECT clp.* " +
      "FROM category_lowest_price clp " +
      "JOIN category c ON clp.category_id = c.category_id " +
      "JOIN brand b ON clp.brand_id = b.brand_id " +
      "WHERE clp.lowest_price = (" +
      "    SELECT MIN(clp2.lowest_price) " +
      "    FROM category_lowest_price clp2 " +
      "    JOIN category c2 ON clp2.category_id = c2.category_id " +
      "    WHERE c2.category_name = :categoryName " +
      ") " +
      "AND b.delete_yn = false " +
      "AND c.delete_yn = false",
      nativeQuery = true)
  Optional<CategoryLowPrice> findLowestPriceByCategoryName(@Param("categoryName") String categoryName);

}
