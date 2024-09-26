package com.musinsa.domain.brand.repository;

import com.musinsa.domain.brand.entitiy.Brand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
  Optional<Brand> findByBrandIdAndDeleteYnFalse(Long brandId);
}
