package com.example.VliVliTicketing.repository;

import com.example.VliVliTicketing.entity.Ticket;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {
  Flux<Ticket> findByTitleContainingIgnoreCaseAndCreatedByContainingIgnoreCase(@NotNull(message = "Ticket Title is required") @NotEmpty(message = "Ticket Title is required") @Size(min = 3, max= 100, message = "Title must be at 3-100 characters") String title, @NotNull(message = "Ticket Creator is required") String createdBy);
  Flux<Ticket> findByCreatedByContainingIgnoreCase(String username);
  Flux<Ticket> findByTitleContainingIgnoreCase(String title);
}
