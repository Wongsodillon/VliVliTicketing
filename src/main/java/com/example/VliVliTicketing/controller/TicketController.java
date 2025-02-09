package com.example.VliVliTicketing.controller;

import com.example.VliVliTicketing.entity.Ticket;
import com.example.VliVliTicketing.response.RestListResponse;
import com.example.VliVliTicketing.response.RestResponse;
import com.example.VliVliTicketing.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Date;

@RestController
@RequestMapping("/tickets")
@Tag(name="Ticket Controller", description = "APIs for Ticket Operations")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @GetMapping("/")
  @Operation(description = "Get all tickets with filters (optional)")
  public Mono<RestListResponse<Ticket>> getTickets(
      @RequestParam(value="keyword", required = false) String keyword,
      @RequestParam(value="username", required = false) String username,
      @RequestParam(value="isAscending", required = false, defaultValue="true") Boolean isAscending
  ) {
    return ticketService.getTickets(keyword, username, isAscending)
        .collectList()
        .flatMap(tickets -> {
            if (tickets.isEmpty()) {
                return Mono.just(new RestListResponse<>(tickets, "No tickets found"));
            } else {
                return Mono.just(new RestListResponse<>(tickets));
            }
        });
  }

  @PostMapping("/")
  @Operation(description = "Create a new ticket")
  public Mono<RestResponse<Ticket>> createTicket(@RequestBody @Valid Ticket ticket) {
    return ticketService.createTicket(ticket)
        .map(newTicket -> new RestResponse<>(newTicket, "Ticket created successfully"));
  }

  @PutMapping("/{id}")
  @Operation(description = "Update an existing ticket")
  public Mono<RestResponse<Ticket>> updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
    return ticketService.getTicket(id)
        .flatMap(existingTicket -> {
          if (ticket.getTitle() != null) existingTicket.setTitle(ticket.getTitle());
          if (ticket.getDescription() != null) existingTicket.setDescription(ticket.getDescription());
          if (ticket.getStatus() != null) existingTicket.setStatus(ticket.getStatus());
          existingTicket.setVersion(existingTicket.getVersion() + 1);
          existingTicket.setUpdatedDate(new Date());
          return ticketService.updateTicket(existingTicket);
        })
        .map(updatedTicket -> new RestResponse<>(updatedTicket, "Ticket updated successfully"))
        .switchIfEmpty(Mono.just(new RestResponse<>(HttpStatus.BAD_REQUEST, "Ticket not found", null)));
  }

  @DeleteMapping("/{id}")
  @Operation(description = "Delete an existing ticket (Soft delete)")
  public Mono<RestResponse<Void>> deleteTicket(@PathVariable String id) {
    return ticketService.deleteTicket(id)
        .flatMap(isSuccess -> isSuccess
            ? Mono.just(new RestResponse<>(HttpStatus.OK, "Comment deleted successfully"))
            : Mono.just(new RestResponse<>(HttpStatus.BAD_REQUEST, "Comment not found"))
        );
  }



}
