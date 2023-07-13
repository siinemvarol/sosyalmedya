package com.sinem.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {
    @Id
    String id;
    /**
     * Bu kullanicinin hangi oturum hesabi ile iliskilendirildiginin bilinmesi gereklidir.
     * Auth kisminda olusturulan kaydin id bilgisi burada tutulmalidir.
     */
    Long authid;
    String username;
    String name;
    String surname;
    String email;
    String profilephoto;
    Long memberSince;
    Long createat;
    Long updateat;
    int state;
}
