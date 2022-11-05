package ru.deevdenis.shorturl.Entity;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
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
    @NonNull
    private String text;
    @NonNull
    private String shortUrl;
    @NonNull
    private Instant timeRegistration;
    @Nullable
    private Instant timeExpired;
    @NonNull
    private String role;
}
