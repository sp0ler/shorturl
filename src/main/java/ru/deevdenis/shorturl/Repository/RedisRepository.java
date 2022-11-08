package ru.deevdenis.shorturl.Repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import ru.deevdenis.shorturl.Entity.ShortUrl;

import java.util.List;

public interface RedisRepository {
    void save(@NonNull ShortUrl message);
    ShortUrl findById(@NonNull String id);
    ShortUrl findByShortUrl(@NonNull String shortUrl);
    List<ShortUrl> findLastTenPublic();
    void deleteById(@NonNull String id);
    List<ShortUrl> findAll();
}
