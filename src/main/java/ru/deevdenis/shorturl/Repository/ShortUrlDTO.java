package ru.deevdenis.shorturl.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.deevdenis.shorturl.Entity.ShortUrl;

import java.time.Instant;
import java.util.List;

public interface ShortUrlDTO extends MongoRepository<ShortUrl, String> {
    ShortUrl findByShortUrl(String shortUrl);

    void deleteAllByTimeExpiredBefore(Instant time);

    List<ShortUrl> findByT
}
