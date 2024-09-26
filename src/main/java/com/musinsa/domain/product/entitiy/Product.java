package com.musinsa.domain.product.entitiy;

import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.category.entitiy.Category;
import com.musinsa.global.jpa.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product", uniqueConstraints = {
    @jakarta.persistence.UniqueConstraint(
        name = "UK_PRODUCT",
        columnNames = { "brand_id", "category_id", "product_name" }
    )
  }, indexes = { //최저가,최고가 조회성능을 높이기 위해 인덱스 설정
    @Index(name = "idx_brand_category_price", columnList = "brand_id, category_id, productPrice")
})
public class Product extends BaseTimeEntity {

  @Id
  @Column(name = "product_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "product_price", nullable = false)
  private Long productPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id")
  private Brand brand;

  @Column
  private Boolean deleteYn = false;

  @PrePersist
  private void prePersist() {
    if (this.deleteYn == null) {
      this.deleteYn = false; // 기본값 설정
    }
  }

  public void updateProduct(Product product) {
    this.brand = product.getBrand();
    this.category = product.getCategory();
    this.productName = product.getProductName();
    this.productPrice = product.getProductPrice();
  }

  public void deleteProduct() {
    this.deleteYn = true;
  }
  // 최저가 비교
  public boolean isLowerThan(Long otherProductPrice) {
    return this.productPrice < otherProductPrice;
  }

  // 최고가 비교
  public boolean isHigherThan(Long otherProductPrice) {
    return this.productPrice > otherProductPrice;
  }

}
