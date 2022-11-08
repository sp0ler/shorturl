package ru.deevdenis.shorturl.Service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.deevdenis.shorturl.Entity.ShortUrl;
import ru.deevdenis.shorturl.Repository.RedisRepository;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@Log4j2
public class ScheduledService {

    @Autowired
    private RedisRepository template;

    @Scheduled(cron = "0 0/1 * * * *")
    public void checkExpired() {
        List<ShortUrl> list = template.findAll();
        for (ShortUrl shortUrl : list) {

            Instant timeExpired = shortUrl.getTimeExpired();
            if (timeExpired != null && Instant.now().isAfter(timeExpired)) {

                String id = shortUrl.getId();
                template.deleteById(id);
                log.info("Auto deleted {} in scheduled method", shortUrl);
            }

        }
    }
}
