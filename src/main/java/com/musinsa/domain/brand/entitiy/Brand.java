package com.musinsa.domain.brand.entitiy;

import com.musinsa.domain.product.entitiy.Product;
import com.musinsa.global.jpa.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brand", uniqueConstraints = {
    @UniqueConstraint(
        name = "UK_BRAND",
        columnNames = { "brand_name" }
    )
})
public class Brand extends BaseTimeEntity {

  @Id
  @Column(name = "brand_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long brandId;

  @Column(name = "brand_name", nullable = false)
  private String brandName;

  @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
  private List<Product> products = new ArrayList<>();

  @Column
  private Boolean deleteYn = false;

  @PrePersist
  private void prePersist() {
    if (this.deleteYn == null) {
      this.deleteYn = false; // 기본값 설정
    }
  }

  public void updateBrandName(String brandName) {
    this.brandName = brandName;
  }

  public void deleteBrand() {
    this.deleteYn = true;
  }
}
