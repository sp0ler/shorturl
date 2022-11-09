package ru.deevdenis.shorturl;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrlTest implements Serializable {
    private String id;
    private String text;
    private String timeRegistration;
    private String timeExpired;
    private String role;
    private long ttl;
}
