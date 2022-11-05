package ru.deevdenis.shorturl.Service;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.deevdenis.shorturl.Entity.ShortUrl;
import ru.deevdenis.shorturl.Repository.ShortUrlDTO;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@Log4j2
public class ShortUrlService {
    @Autowired
    private ShortUrlDTO template;

    public ShortUrl save(@NonNull ShortUrl message, Long timeExpired) {

        ShortUrl newMessage = ShortUrl.builder()
                .id(message.getId())
                .text(message.getText())
                .shortUrl(UUID.randomUUID().toString().substring(0, 15).replace("-", ""))
                .timeRegistration(Instant.now())
                .timeExpired(timeExpired == null ? null : Instant.now().plusMillis(timeExpired))
                .role(message.getRole())
                .build();

        template.save(newMessage);
        log.info(String.format("Added to BD: %s", newMessage));

        return newMessage;
    }

    public ShortUrl fetch(@NonNull String shortUrl) {
        ShortUrl message =  template.findByShortUrl(shortUrl);
        log.info(String.format("Find into BD: %s", message));

        return message;
    }
}
