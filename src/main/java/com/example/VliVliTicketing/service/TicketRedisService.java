package com.example.VliVliTicketing.service;

import com.example.VliVliTicketing.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class TicketRedisService {

  private static final String CACHE_PREFIX = "ticket:";
  private static final Duration CACHE_EXPIRE_TIME = Duration.ofMinutes(30);

  @Autowired
  @Qualifier("reactiveRedisTemplate")
  private ReactiveRedisTemplate<String, List<Ticket>> redisTemplate;

  public String generateCacheKey(String keyword, String username, Boolean isAscending) {
    return CACHE_PREFIX +
        "keyword=" + (keyword != null ? keyword : "all") + ":" +
        "username=" + (username != null ? username : "all") + ":" +
        "sort=" + (isAscending ? "asc" : "desc");
  }

  public Mono<Boolean> put(String key, List<Ticket> ticket) {
    return redisTemplate.opsForValue().set(CACHE_PREFIX + key, ticket, CACHE_EXPIRE_TIME);
  }

  public Mono<List<Ticket>> get(String key) {
    return redisTemplate.opsForValue().get(CACHE_PREFIX + key);
  }

  public Mono<Boolean> delete(String key) {
    return redisTemplate.opsForValue().delete(CACHE_PREFIX + key);
  }

}
