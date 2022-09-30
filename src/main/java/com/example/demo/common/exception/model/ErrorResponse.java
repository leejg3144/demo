package com.example.demo.common.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();

  private Integer status;

  private String code;

  private String message;

  //@Valid의 Parameter 검증을 통과하지 못한 필드가 담긴다.
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("errors")
  private List<CustomFieldError> customFieldErrors;

  public ErrorResponse(ErrorCode code) {
    this.status = code.getStatus();
    this.code = code.getCode();
    this.message = code.getMessage();
  }

  public ErrorResponse errors(Errors errors) {
    setCustomFieldErrors(errors.getFieldErrors());
    return this;
  }

  public void setCustomFieldErrors(List<FieldError> fieldErrors) {
    customFieldErrors = new ArrayList<>();

    fieldErrors.forEach(error -> {
      customFieldErrors.add(new CustomFieldError(
          error.getCodes()[0],
          error.getRejectedValue(),
          error.getDefaultMessage()
      ));
    });
  }

  @Data
  public static class CustomFieldError {
    private String field;
    private Object value;
    private String reason;

    public CustomFieldError(String field, Object value, String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }
  }

}
