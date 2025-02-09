package com.example.VliVliTicketing.controller;

import com.example.VliVliTicketing.entity.Comment;
import com.example.VliVliTicketing.response.RestListResponse;
import com.example.VliVliTicketing.response.RestResponse;
import com.example.VliVliTicketing.service.CommentService;
import com.example.VliVliTicketing.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comments")
@Tag(name="Comment Controller", description = "APIs for Comment Operations")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @Autowired
  private TicketService ticketService;

  @GetMapping("/{id}")
  public Mono<RestListResponse<Comment>> getComments(@PathVariable("id") String ticketId) {
    return ticketService.getTicket(ticketId)
            .flatMap(ticket -> commentService.getComments(ticketId)
                      .collectList()
                      .map(RestListResponse::new)
            )
            .switchIfEmpty(Mono.just(new RestListResponse<>(HttpStatus.NOT_FOUND, "No Tickets Found")));
  }

  @PostMapping("/")
  public Mono<RestResponse<Comment>> createComment(@RequestBody @Valid Comment comment) {
    return commentService.createComment(comment)
        .map(newComment -> new RestResponse<>(newComment, "Comment created successfully"));
  }

  @DeleteMapping("/{id}")
  public Mono<RestResponse<Void>> deleteComment(@PathVariable String id) {
    return commentService.deleteComment(id)
        .flatMap(deleted -> deleted
            ? Mono.just(new RestResponse<>(HttpStatus.OK, "Comment deleted successfully"))
            : Mono.just(new RestResponse<>(HttpStatus.BAD_REQUEST, "Comment not found"))
        );
  }


}
