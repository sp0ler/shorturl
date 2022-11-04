package ru.deevdenis.shorturl.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrl {
    @Id
    private String id;
    private String text;
    private String shortUrl;
    private Instant timeRegistration;
    private Instant timeExpired;
    private String role;
}
