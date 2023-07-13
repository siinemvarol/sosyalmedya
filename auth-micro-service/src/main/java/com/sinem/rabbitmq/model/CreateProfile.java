package com.sinem.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ÇOOKK ÖNEMLİ !!!
 * RabbitMQ gibi yapılar sınıfları iletirken base64'e çevirir. Bu nedenle
 * bu sınıfları tanımlarken serileştirme işlemlerini yapmamız gerekir. Ayrıca
 * dikkat edilmesi gereken diğer husus, serileştirilen sınıfların iletilen tarafta
 * serileştirme işlemini tersine işletebilmesi için aynı paket adı içinde
 * tanımlanmış olması gereklidir. Aksi halde hata alınır. *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProfile implements Serializable {
    Long authId;
    String username;
    String email;
}
