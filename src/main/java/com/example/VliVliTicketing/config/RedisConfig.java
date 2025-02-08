package com.example.VliVliTicketing.config;

import com.example.VliVliTicketing.entity.Comment;
import com.example.VliVliTicketing.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//  @Autowired
//  RedisConnectionFactory factory;
//
//  @Bean
//  public ReactiveRedisTemplate<String, Ticket> reactiveRedisTicketTemplate(ReactiveRedisConnectionFactory factory) {
//      Jackson2JsonRedisSerializer<Ticket> serializer = new Jackson2JsonRedisSerializer<>(Ticket.class);
//
//      RedisSerializationContext.RedisSerializationContextBuilder<String, Ticket> builder =
//          RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
//
//      RedisSerializationContext<String, Ticket> context = builder.value(serializer)
//          .build();
//
//      return new ReactiveRedisTemplate<>(factory, context);
//  }
//
//  @Bean
//  public ReactiveRedisTemplate<String, Comment> reactiveRedisCommentTemplate(ReactiveRedisConnectionFactory factory) {
//    Jackson2JsonRedisSerializer<Comment> serializer = new Jackson2JsonRedisSerializer<>(Comment.class);
//
//    RedisSerializationContext.RedisSerializationContextBuilder<String, Comment> builder =
//        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
//
//    RedisSerializationContext<String, Comment> context = builder.value(serializer)
//        .build();
//
//    return new ReactiveRedisTemplate<>(factory, context);
//  }

}
