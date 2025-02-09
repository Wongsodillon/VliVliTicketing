package com.example.VliVliTicketing.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class RestListResponse<T> extends RestResponse<List<T>> {

  private List<T> data;

  public RestListResponse(List<T> data) {
    super(data);
    this.data = data;
  }

  public RestListResponse(List<T> data, String message) {
    super(data, message);
    this.data = data;
  }

  public RestListResponse(HttpStatus httpStatus, String message) {
    super(httpStatus, message);
  }
}
