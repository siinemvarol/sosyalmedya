package com.sinem.controller;

import com.sinem.dto.request.DoLoginRequestDto;
import com.sinem.dto.request.DoRegisterRequestDto;
import com.sinem.dto.response.DoLoginResponseDto;
import com.sinem.dto.response.DoRegisterResponseDto;
import com.sinem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sinem.constants.RestApis.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        Boolean isLogin = authService.login(dto);
        if(isLogin){
            return ResponseEntity.ok(DoLoginResponseDto
                    .builder()
                            .status(200)
                            .result("Giriş işlemi başarılı")
                    .build());
        }
        return ResponseEntity.badRequest().body(
                DoLoginResponseDto
                        .builder()
                        .status(400)
                        .result("Giriş işlemi başarısız. Lütfen bilgilerinizi kontrol ederek tekrar deneyiniz.")
                        .build()
        );
    }

    @PostMapping(REGISTER)
    public ResponseEntity<DoRegisterResponseDto> doRegister(@RequestBody @Valid DoRegisterRequestDto dto){
        Boolean isRegister = authService.register(dto);
        if(isRegister){
            return ResponseEntity.ok(DoRegisterResponseDto
                    .builder()
                            .status(200)
                            .result("Kayıt işlemi başarılı")
                    .build());
        }
        return ResponseEntity.badRequest().body(
                DoRegisterResponseDto
                        .builder()
                        .status(400)
                        .result("Kayıt işlemi başarısız. Lütfen tekrar deneyin.")
                        .build()
        );
    }
}
