package com.example.demo.common.exception;

import com.example.demo.common.exception.model.ErrorResponse;
import com.example.demo.common.exception.model.InvalidParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(InvalidParamException.class)
  protected ResponseEntity<ErrorResponse> handleInvalidParamException(InvalidParamException e) {
    log.warn("#### handle InvalidParamException", e);

    ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
    // 파라미터 검증 결과 추가
    errorResponse.errors(e.getErrors());

    return new ResponseEntity<>(errorResponse, HttpStatus.resolve(errorResponse.getStatus()));
  }

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
    log.warn("#### handle CustomException", e);

    ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());

    return new ResponseEntity<>(errorResponse, HttpStatus.resolve(errorResponse.getStatus()));
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.warn("#### handle Exception", e);

    ErrorResponse errorResponse = ErrorResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(e.getLocalizedMessage())
        .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
