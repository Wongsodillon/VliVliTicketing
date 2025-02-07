package com.example.VliVliTicketing.entity;

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
  private String ticketId;
  private String username;
  private String comment;
  private Date createdDate;
  private String createdBy;
  private Integer version;
  private Boolean markForDelete;
}
