package com.example.VliVliTicketing.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {
  @Id
  private String id;

  @NotNull(message = "Ticket Id is required")
  private String ticketId;

  @NotNull(message = "Username is required")
  @Size(min = 3, message = "Username must be at least 3 characters")
  private String username;

  @NotNull(message = "Comment is required")
  @Size(min = 3, message = "Comment must be at least 3 characters")
  private String comment;
  private Date createdDate;

  @NotNull(message = "createdBy is required")
  private String createdBy;

  @NotNull(message = "Version is required")
  @Size(min = 1, message = "Version must be at least 1")
  private Integer version;

  private Boolean markForDelete;
}
