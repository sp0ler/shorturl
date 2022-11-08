package ru.deevdenis.shorturl.Service;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.deevdenis.shorturl.Entity.ShortUrl;
import ru.deevdenis.shorturl.Repository.RedisRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Log4j2
public class ShortUrlService {
    @Autowired
    private RedisRepository template;

    public ShortUrl save(@NonNull ShortUrl message, @NonNull Long timeExpired) {
        ShortUrl newMessage = ShortUrl.builder()
                .id(
                    String.format(
                            "%s%s",
                            UUID.randomUUID().toString().substring(0, 5),
                            Instant.now().toString().substring(15, 25)
                    ).replace(":", "").replace(".", "")
                )
                .text(message.getText())
                .timeRegistration(Instant.now())
                .timeExpired(Instant.now().plusMillis(timeExpired))
                .role(message.getRole())
                .ttl(timeExpired)
                .build();

        template.save(newMessage);
        log.info("Added to BD: {}", newMessage);

        return newMessage;
    }

    public ShortUrl fetch(@NonNull String shortUrl) {
        ShortUrl message =  template.findByShortUrl(shortUrl);
        log.info("Find into BD: {}", message);

        return message;
    }

    public List<ShortUrl> findLastTenPublic() {
        log.info("Find last 10 public into BD");
        return template.findLastTenPublic();
    }

    public void delete(String id) {
        template.deleteById(id);
        log.info("Deleted {}", id);
    }
}
