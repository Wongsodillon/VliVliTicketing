package com.example.VliVliTicketing.entity;

import com.example.VliVliTicketing.enums.TicketStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tickets")
public class Ticket {
  @Id
  private String id;

  @NotNull(message = "Ticket Title is required")
  @NotEmpty(message = "Ticket Title is required")
  @Size(min = 3, max= 100, message = "Title must be at 3-100 characters")
  private String title;

  @Enumerated(EnumType.STRING)
  @NotNull(message = "Ticket Status is required")
  private TicketStatus status;

  @NotNull(message = "Ticket Description is required")
  @NotEmpty(message = "Ticket Description is required")
  @Size(min = 3, max= 1000, message = "Description must be at 3-1000 characters")
  private String description;
  private Date createdDate;

  @NotNull(message = "Ticket Creator is required")
  private String createdBy;

  @NotNull(message = "Version is required")
  private Integer version;
  private Date updatedDate;

  @NotNull(message = "Ticket Updater is required")
  private String updatedBy;
  private Boolean markForDelete;
}
