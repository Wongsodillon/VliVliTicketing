package com.example.VliVliTicketing.repository;

import com.example.VliVliTicketing.entity.Ticket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<Ticket, String> {
  Flux<Ticket> findByTitleContainingIgnoreCaseOrCreatedByContainingIgnoreCase(String title, String username);
  Flux<Ticket> findByCreatedByContainingIgnoreCase(String username);
  Flux<Ticket> findByTitleContainingIgnoreCase(String title);
}
