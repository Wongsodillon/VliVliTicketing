package com.example.VliVliTicketing.service;

import com.example.VliVliTicketing.entity.Comment;
import com.example.VliVliTicketing.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public Flux<Comment> getComments(String ticketId) {
    return commentRepository.findByTicketId(ticketId).switchIfEmpty(Flux.empty());
  }

  public Mono<Comment> createComment(Comment comment) {
    return commentRepository.save(comment);
  }

  public Mono<Boolean> deleteComment(String id) {
    return commentRepository.findById(id)
        .flatMap(comment -> {
          comment.setMarkForDelete(true);
          return commentRepository.save(comment).thenReturn(true);
        }).defaultIfEmpty(false);
  }

}
