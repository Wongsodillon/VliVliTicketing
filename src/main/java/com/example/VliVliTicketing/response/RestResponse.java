package com.example.VliVliTicketing.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestResponse<T> {

  private Integer code;

  private String status;

  private T data;

  private String message;

  public RestResponse(T data) {
    this(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data, HttpStatus.OK.getReasonPhrase());
  }

  public RestResponse(T data, String message) {
    this(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data, message);
  }

  public RestResponse(HttpStatus httpStatus, String message) {
    this(httpStatus.value(), httpStatus.getReasonPhrase(), null, message);
  }

  public RestResponse(HttpStatus httpStatus, String message, T data) {
    this(httpStatus.value(), httpStatus.getReasonPhrase(), data, message);
  }
}

