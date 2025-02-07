package com.example.VliVliTicketing.repository;

import com.example.VliVliTicketing.entity.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
  Flux<Comment> findByTicketId(String ticketId);
}
