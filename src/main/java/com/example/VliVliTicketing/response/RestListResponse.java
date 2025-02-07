package com.example.VliVliTicketing.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Schema(name = "RestListResponse", description = "Standard API list response")
public class RestListResponse<T> {

  @Schema(description = "List of response items")
  private List<T> data;

  public RestListResponse(List<T> data) {
    this.data = data;
  }
}
