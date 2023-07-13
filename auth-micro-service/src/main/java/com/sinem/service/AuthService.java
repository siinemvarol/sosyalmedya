package com.sinem.service;

import com.sinem.dto.request.DoLoginRequestDto;
import com.sinem.dto.request.DoRegisterRequestDto;
import com.sinem.dto.request.UserSaveRequestDto;
import com.sinem.exceptions.AuthException;
import com.sinem.exceptions.ErrorType;
import com.sinem.manager.IUserManager;
import com.sinem.mapper.IAuthMapper;
import com.sinem.repository.IAuthRepository;
import com.sinem.repository.entity.Auth;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final IUserManager userManager;

    public AuthService(IAuthRepository authRepository, IUserManager userManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager = userManager;
    }

    /**
     * Register a new user
     * Login
     * Not:
     * - Kayit olurken eger ayni kullanici adi var ise hata donecek
     * - Kayit basarili ise olumlu sonuc donulecek
     * - Giris yapan kullaniciya bir JWT token dondurulecek
     * - Giriste sorun olursa sorun bilgisi DTO olarak dondurulecek
     */

    public Boolean login(DoLoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        /**
         * DİKKAT!!! burada iki yolumuz var;
         * 1 - Auth bilgisini sorgulayarak kullanici yok ise false donebiliriz.
         *          if(auth.isEmpty()){
         *                       return false;
         *                  }
         *                  return auth.isPresent();
         * 2 - Auth bilgisini sorgulayarak kullanici yok ise ya da bilgileri yanlis ise exception firlatabiliriz.
         */
        if (auth.isEmpty()) {
            throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
        }
        return true;
    }

    public Optional<Auth> loginAlternatif(DoLoginRequestDto dto) {
        return authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }

    public Boolean register(DoRegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {    // Eger sifre ile sifre dogrulama eslesmiyorsa hata
            throw new AuthException(ErrorType.REGISTER_PASSWORDS_NOT_MATCH);
        }
        authRepository.findOptionalByUsername(dto.getUsername())    // Eger kullanici adi var ise hata firlat
                .ifPresent(auth -> {
                    throw new AuthException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
                });
        /**
         *    Auth auth = Auth
         *                 .builder()
         *                 .email(dto.getEmail())
         *                 .username(dto.getUsername())
         *                 .password(dto.getPassword())
         *                 .build();
         */
        Auth auth = IAuthMapper.INSTANCE.authFromDto(dto);
        authRepository.save(auth);
        /**
         * Auth servis kullaniciyi kayit ettikten sonra user microserviceine kullanici profili
         * olusturulmak uzere bilgi gonderir.
         */
        userManager.save(UserSaveRequestDto.builder()
                .authid(auth.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .build());
        return true;
    }
}
