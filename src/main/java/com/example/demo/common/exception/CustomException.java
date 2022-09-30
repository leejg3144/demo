package com.example.demo.common.exception;

import com.example.demo.common.exception.model.ErrorCode;
import lombok.Getter;

public class CustomException extends RuntimeException {

  private static final Long UUID = 1L;

  @Getter
  private ErrorCode errorCode;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
