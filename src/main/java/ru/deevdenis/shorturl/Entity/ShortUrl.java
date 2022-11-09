package ru.deevdenis.shorturl.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.lang.Nullable;


import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Shorturl")
public class ShortUrl implements Serializable {
    @Id
    private String id;
    @NonNull
    private String text;
    @NonNull
    private Instant timeRegistration;
    @Nullable
    private Instant timeExpired;
    @NonNull
    private String role;
    @NonNull
    private long ttl;
}
