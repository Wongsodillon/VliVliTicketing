package com.example.VliVliTicketing.enums;

import com.example.VliVliTicketing.exception.EnumException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum TicketStatus {
  ACTIVE,
  PENDING,
  CLOSED;

  @JsonCreator
  public static TicketStatus fromString(String value) {
    for (TicketStatus status : TicketStatus.values()) {
      if (status.name().equalsIgnoreCase(value)) {
        return status;
      }
    }
    throw new EnumException(
        "Invalid Ticket Status: " + value + ". Allowed values are: " + Arrays.toString(TicketStatus.values())
    );
  }

  @JsonValue
  public String toJson() {
    return this.name().toUpperCase();
  }
}
