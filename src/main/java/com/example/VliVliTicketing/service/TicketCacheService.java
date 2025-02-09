package com.example.VliVliTicketing.service;

import com.example.VliVliTicketing.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class TicketCacheService {

    private static final String CACHE_PREFIX = "tickets:";
    private static final Logger log = LoggerFactory.getLogger(TicketCacheService.class);

    private final ReactiveRedisTemplate<String, List<Ticket>> redisTemplate;

    public TicketCacheService(ReactiveRedisTemplate<String, List<Ticket>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String generateCacheKey(String keyword, String username, Boolean isAscending) {
        StringBuilder builder = new StringBuilder(CACHE_PREFIX);

        if (keyword != null && !keyword.isEmpty()) {
            builder.append(String.format("keyword:%s:", keyword));
        }
        if (username != null && !username.isEmpty()) {
            builder.append(String.format("username:%s:", username));
        }
        log.info(builder.toString());
        return builder.toString();
    }

    public Mono<Boolean> put(String key, List<Ticket> value) {
        log.debug("put: key={}, value={}", key, value);
        return redisTemplate.opsForValue()
                .set(key, value)
                .flatMap(success -> redisTemplate.expire(key, Duration.ofHours(1)));
    }

    public Mono<List<Ticket>> get(String key) {
        return redisTemplate.opsForValue().get(key)
                .doOnSubscribe(subscription -> log.info("🔍 Checking Redis for key: {}", key))
                .doOnNext(data -> {
                    if (data != null) {
                        log.info("✅ CACHE HIT: Data found in Redis for key: {}", key);
                    } else {
                        log.warn("⚠️ CACHE MISS: No data found in Redis for key: {}", key);
                    }
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Boolean> remove(String key) {
        return redisTemplate.delete(key).map(count -> count > 0);
    }
}
