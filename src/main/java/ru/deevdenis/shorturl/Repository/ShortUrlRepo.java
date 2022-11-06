package ru.deevdenis.shorturl.Repository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.Nullable;
import ru.deevdenis.shorturl.Entity.ShortUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ShortUrlRepo {
    @Autowired
    private RedisTemplate<String, Object> template;

    private static final String HASH_KEY = "Shorturl";

    public ShortUrl save(@NonNull ShortUrl message) {
        template.opsForHash().put(HASH_KEY, message.getId(), message);
        return message;
    }

    @Nullable
    public ShortUrl findById(Long id) {
        return (ShortUrl) template.opsForHash().get(HASH_KEY, id);
    }

    @Nullable
    public ShortUrl findByShortUrl(String shortUrl) {
        return (ShortUrl) template.opsForHash().get(HASH_KEY, shortUrl);
    }
}
