package com.example.demo.common.exception.model;

import com.example.demo.common.exception.CustomException;
import lombok.Getter;
import org.springframework.validation.Errors;

public class InvalidParamException extends CustomException {

  private static final Long UUID = -2116671122895194101L;

  @Getter
  private Errors errors;

  public InvalidParamException(Errors errors) {
    super(ErrorCode.INVALID_PARAM);
    this.errors = errors;
  }
}
