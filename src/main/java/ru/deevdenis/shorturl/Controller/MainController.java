package ru.deevdenis.shorturl.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.deevdenis.shorturl.Entity.ShortUrl;
import ru.deevdenis.shorturl.Service.ShortUrlService;

@RestController
public class MainController {

    @Autowired
    private ShortUrlService shortUrlService;

    public static final String FETCH = "/api/fetch/{short_url}";
    public static final String SAVE = "/api/save/{time_expired}";

    @PostMapping(SAVE)
    @ResponseBody
    public ResponseEntity<ShortUrl> save(
            @RequestBody ShortUrl message,
            @PathVariable("time_expired") Long timeExpired) {
        return ResponseEntity.ok(shortUrlService.save(message, timeExpired));
    }

    @GetMapping(FETCH)
    @ResponseBody
    public ResponseEntity<ShortUrl> fetch(@PathVariable("short_url") String shortUrl) {
        return ResponseEntity.ok(shortUrlService.fetch(shortUrl));
    }
}
