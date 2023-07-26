package com.sinem.controller;

import com.sinem.dto.request.DoLoginRequestDto;
import com.sinem.dto.request.DoRegisterRequestDto;
import com.sinem.dto.response.DoLoginResponseDto;
import com.sinem.dto.response.DoRegisterResponseDto;
import com.sinem.rabbitmq.model.CreateProfile;
import com.sinem.rabbitmq.producer.CreateProfileProducer;
import com.sinem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sinem.constants.RestApis.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CreateProfileProducer createProfileProducer;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Merhaba bu servis AUTH servisidir.");
    }
    @GetMapping("/testrabbit")
    public ResponseEntity<Void> testRabbitSendMessage(String username, String email, Long authId){
        createProfileProducer.sendCreateProfileMessage(
                CreateProfile.builder()
                        .authId(authId)
                        .email(email)
                        .username(username)
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        String token = authService.login(dto);
        return ResponseEntity.ok(DoLoginResponseDto.builder()
                .status(200)
                .result("Giriş işlemi başarılı.")
                .token(token)
                .build());

    }

    @PostMapping(REGISTER)
    @CrossOrigin("*")
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
