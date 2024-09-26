package com.musinsa.global.constants;

public class ExceptionConstants {

  public static class BrandExceptionConstants {
    public static final String CODE_BRAND_NOT_FOUND = "BRAND_NOT_FOUND";
    public static final String MESSAGE_BRAND_NOT_FOUND = "브랜드를 찾을 수 없습니다.";
    public static final String CODE_ALREADY_BRAND = "ALREADY_BRAND";
    public static final String MESSAGE_ALREADY_BRAND = "이미 브랜드가 등록되었습니다.";
  }
  public static class CategoryExceptionConstants {
    public static final String CODE_CATEGORY_NOT_FOUND = "CATEGORY_NOT_FOUND";
    public static final String MESSAGE_CATEGORY_NOT_FOUND = "카테고리를 찾을 수 없습니다.";
  }

  public static class ProductExceptionConstants {
    public static final String CODE_PRODUCT_NOT_FOUND = "BRAND_NOT_PRODUCT";
    public static final String MESSAGE_PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다.";
    public static final String CODE_ALREADY_PRODUCT = "ALREADY_PRODUCT";
    public static final String MESSAGE_ALREADY_PRODUCT = "같은 상품이 등록되었습니다.";
    public static final String CODE_PRODUCTS_NOT_EXIST = "CODE_PRODUCTS_NOT_EXIST";
    public static final String MESSAGE_PRODUCTS_NOT_EXIST = "등록된 상품들이 존재하지 않습니다.";
  }

  public static class ApplicationExceptionConstants {
    public static final String CODE_APPLICATION_UNKNOWN = "APPLICATION_UNKNOWN_ERROR";
    public static final String MESSAGE_APPLICATION_UNKNOWN = "알수없는 오류 입니다.";
  }

}
