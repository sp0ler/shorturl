package ru.deevdenis.shorturl.Repository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.deevdenis.shorturl.Entity.ShortUrl;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RedisRepositoryImpl implements RedisRepository{
    @Autowired
    private RedisTemplate<String, Object> template;

    private static final String HASH_KEY = "Shorturl";

    public void save(@NonNull ShortUrl message) {
        template.opsForHash().put(HASH_KEY, message.getId(), message);
    }

    @Nullable
    public ShortUrl findById(@NonNull String id) {
        return (ShortUrl) template.opsForHash().get(HASH_KEY, id);
    }

    @Nullable
    public ShortUrl findByShortUrl(@NonNull String shortUrl) {
        return (ShortUrl) template.opsForHash().get(HASH_KEY, shortUrl);
    }

    // TODO: иначе
    public List<ShortUrl> findLastTenPublic() {
        String role = "public";

        return template.opsForHash().values(HASH_KEY).stream()
                .map(it -> (ShortUrl) it)
                .filter(it -> it.getRole().equals(role))
                .sorted(
                    (x1, x2) -> (int) x2.getTimeRegistration().getEpochSecond() -
                            (int) x1.getTimeRegistration().getEpochSecond()
                )
                .limit(10)
                .collect(Collectors.toList());
    }

    public void deleteById(@NonNull String id) {
        template.opsForHash().delete(HASH_KEY, id);
    }

    public List<ShortUrl> findAll() {
        return template.opsForHash().values(HASH_KEY).stream()
                .map(it -> (ShortUrl) it)
                .collect(Collectors.toList());
    }

}
