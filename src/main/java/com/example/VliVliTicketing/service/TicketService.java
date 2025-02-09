package com.example.VliVliTicketing.service;

import com.example.VliVliTicketing.entity.Ticket;
import com.example.VliVliTicketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private TicketCacheService ticketCacheService;

  public Flux<Ticket> getTickets(String keyword, String username, Boolean isAscending) {

    String cacheKey = ticketCacheService.generateCacheKey(keyword, username, isAscending);
//    return fetchFromDatabase(keyword, username, isAscending);
    return ticketCacheService.get(cacheKey)
            .flatMapMany(Flux::fromIterable)
            .switchIfEmpty(
                    fetchFromDatabase(keyword, username, isAscending)
                            .collectList()
                            .flatMapMany(tickets ->
                                    ticketCacheService.put(cacheKey, tickets)
                                    .thenMany(Flux.fromIterable(tickets))
                            )
            );

  }

  public Flux<Ticket> fetchFromDatabase(String keyword, String username, Boolean isAscending) {
    Flux<Ticket> tickets;

    if (keyword != null && !keyword.isEmpty() && username != null && !username.isEmpty()) {
      tickets = ticketRepository.findByTitleContainingIgnoreCaseAndCreatedByContainingIgnoreCase(keyword, username);
    }
    else if (keyword != null && !keyword.isEmpty()) {
      tickets = ticketRepository.findByTitleContainingIgnoreCase(keyword);
    }
    else if (username != null && !username.isEmpty()) {
      tickets = ticketRepository.findByCreatedByContainingIgnoreCase(username);
    }
    else {
      tickets = ticketRepository.findAll();
    }

    if (isAscending == null) {
      return tickets;
    }

    Comparator<Ticket> comparator = isAscending
            ? Comparator.comparing(Ticket::getCreatedDate)
            : Comparator.comparing(Ticket::getCreatedDate).reversed();
    return tickets.sort(comparator);
  }

  public Mono<Ticket> createTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  public Mono<Ticket> getTicket(String id) {
    return ticketRepository.findById(id).switchIfEmpty(Mono.empty());
  }

  public Mono<Ticket> updateTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  public Mono<Boolean> deleteTicket(String id) {
    return ticketRepository.findById(id)
        .flatMap(ticket -> {
          ticket.setMarkForDelete(true);
          return ticketRepository.save(ticket).thenReturn(true);
        }).defaultIfEmpty(false);
  }

}
