package com.example.demo.common.exception.model;

import lombok.Getter;

@Getter
public enum ErrorCode {
  LOGOS_NOT_FOUND(404, "C001","말씀 데이터를 찾을 수 없습니다."),
  INVALID_PARAM(400, "C002", "유효하지 않은 파라미터 입니다.");

  private Integer status;

  private String code;

  private String message;

  ErrorCode(Integer status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
