package com.example.VliVliTicketing.response;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomErrorResponse {

  private Integer code;

  private String status;

  private String message;

  private Map<String, String> errors;

  public CustomErrorResponse(HttpStatus httpStatus, String message) {
    this(httpStatus.value(), httpStatus.getReasonPhrase(), message, null);
  }
}

