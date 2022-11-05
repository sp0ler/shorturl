package ru.deevdenis.shorturl.Service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.deevdenis.shorturl.Entity.ShortUrl;
import ru.deevdenis.shorturl.Repository.ShortUrlDTO;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@Log4j2
public class ScheduleService {

    @Autowired
    private ShortUrlDTO template;

    @Scheduled(cron = "0/6 * * * * *")
    public void checkTimeExpired() {
        List<ShortUrl> listByTimeExpiredBefore = template.findByTimeExpiredBefore(Instant.now());
        template.deleteAll(listByTimeExpiredBefore);
        for (ShortUrl shortUrl : listByTimeExpiredBefore) {
            log.info(String.format("Message has been auto deleted when time expired: %s", shortUrl));
        }

    }
}
