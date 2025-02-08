package com.example.VliVliTicketing.response;

import lombok.Data;
import java.util.List;

@Data
public class RestListResponse<T> extends RestResponse<List<T>> {

  private List<T> data;

  public RestListResponse(List<T> data) {
    super(data);
    this.data = data;
  }
}
