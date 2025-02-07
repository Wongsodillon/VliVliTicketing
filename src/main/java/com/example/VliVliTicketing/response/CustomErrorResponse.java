package com.example.VliVliTicketing.response;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "CustomErrorResponse", description = "Standard API error response")
public class CustomErrorResponse {

  @Schema(description = "Response code", example = "400")
  private Integer code;

  @Schema(description = "Response status", example = "BAD_REQUEST")
  private String status;

  @Schema(description = "Error message", example = "Validation failed")
  private String message;

  @Schema(description = "Field validation errors")
  private Map<String, String> errors;

  public CustomErrorResponse(HttpStatus httpStatus, String message) {
    this(httpStatus.value(), httpStatus.getReasonPhrase(), message, null);
  }
}

