package com.musinsa.domain.product;

import com.musinsa.api.product.dto.ProductDto.SaveProductRequest;
import com.musinsa.domain.brand.entitiy.Brand;
import com.musinsa.domain.brand.service.BrandService;
import com.musinsa.domain.category.entitiy.Category;
import com.musinsa.domain.category.service.CategoryService;
import com.musinsa.domain.product.entitiy.Product;
import com.musinsa.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

  private final ProductService productService;
  private final CategoryService categoryService;
  private final BrandService brandService;

  @Transactional
  public Product createProduct(SaveProductRequest saveProductRequest) {
    Product product = createProductFromProductRequest(saveProductRequest);
    return productService.createProduct(product);
  }

  @Transactional
  public Product updateProduct(Long productId, SaveProductRequest saveProductRequest) {
    Product product = createProductFromProductRequest(saveProductRequest);
    return productService.updateProduct(productId, product);
  }

  @Transactional
  public Product deleteProduct(Long productId) {
    return productService.deleteProduct(productId);
  }

  private Product createProductFromProductRequest(SaveProductRequest saveProductRequest) {
    Category category = categoryService.findByCategoryId(saveProductRequest.getCategoryId());
    Brand brand = brandService.findByBrandId(saveProductRequest.getBrandId());

    return Product.builder()
        .brand(brand)
        .category(category)
        .productName(saveProductRequest.getProductName())
        .productPrice(saveProductRequest.getProductPrice())
        .build();
  }

}
