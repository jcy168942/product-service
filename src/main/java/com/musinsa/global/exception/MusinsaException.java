package com.musinsa.global.exception;

import lombok.Getter;

@Getter
public class MusinsaException extends RuntimeException {

  String code;

  public MusinsaException(String code, String message) {
    super(message);
    this.code = code;
  }

}
