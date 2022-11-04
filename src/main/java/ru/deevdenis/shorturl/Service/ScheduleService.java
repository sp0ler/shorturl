package ru.deevdenis.shorturl.Service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.deevdenis.shorturl.Repository.ShortUrlDTO;

import java.time.Instant;

@Service
@Transactional
@Log4j2
public class ScheduleService {

    @Autowired
    private ShortUrlDTO template;

    @Scheduled(cron = "0/6 * * * * *")
    public void checkTimeExpired() {
        template.deleteAllByTimeExpiredBefore(Instant.now());
        log.info("Messages has been auto deleted when time expired");
    }
}
