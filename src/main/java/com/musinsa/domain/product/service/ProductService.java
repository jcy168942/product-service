package com.musinsa.domain.product.service;

import static com.musinsa.global.constants.ExceptionConstants.ProductExceptionConstants.CODE_PRODUCT_NOT_FOUND;
import static com.musinsa.global.constants.ExceptionConstants.ProductExceptionConstants.MESSAGE_PRODUCT_NOT_FOUND;

import com.musinsa.domain.product.entitiy.Product;
import com.musinsa.domain.product.repository.ProductRepository;
import com.musinsa.global.exception.MusinsaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepository;

  // 상품 객체 생성
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long productId, Product newProduct) {
    Product product = findProductById(productId);
    product.updateProduct(newProduct);
    // Product 저장
    return productRepository.save(product);
  }

  // 상품 삭제
  public Product deleteProduct(Long productId) {
    Product product = findProductById(productId);
    product.deleteProduct();
    productRepository.save(product);
    return product;
  }

  // 상품 객체 id 조회
  public Product findProductById(Long productId) {
    return productRepository.findByProductIdAndDeleteYnFalse(productId)
        .orElseThrow(() -> new MusinsaException(CODE_PRODUCT_NOT_FOUND, MESSAGE_PRODUCT_NOT_FOUND));
  }

}
