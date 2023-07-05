package com.sinem.hastanerandevu.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    INTERNAL_SERVER_ERROR(1000, "Sunucuda bilinmeyen bir hata olustu.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001, "Istek formati hatali", HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(1001, "Kullanici adi kayitli", HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1001, "Name alani bos gecilemez", HttpStatus.BAD_REQUEST),
    DOKTOR_BULUNAMADI(3001, "Doktor bulunamadi", HttpStatus.BAD_REQUEST),

    HASTA_BULUNAMADI(4001, "Hasta bulunamadi", HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(1001, "Aradığınız id'ye ait kayit bulunamamıştır", HttpStatus.BAD_REQUEST),
    BRANS_ZATEN_KAYITLI(2001, "Bu brans zaten kayitlidir", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1002, "Geçersiz token", HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus httpStatus;
}
