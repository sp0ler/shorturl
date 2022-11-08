package ru.deevdenis.shorturl.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.lang.Nullable;


import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.TimeUnit;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Shorturl", timeToLive = 1L)
public class ShortUrl implements Serializable {
    @Id
    private String id;
    @NonNull
    private String text;
    @NonNull
    private String shortUrl;
    @NonNull
    private Instant timeRegistration;
    @Nullable
    private Instant timeExpired;
    @NonNull
    private String role;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private long ttl;
}
