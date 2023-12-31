package com.sinem.controller;

import com.sinem.dto.request.UserSaveRequestDto;
import com.sinem.dto.response.UserSaveResponseDto;
import com.sinem.repository.entity.User;
import com.sinem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sinem.constants.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Merhaba bu servis USER servisidir.");
    }

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok(UserSaveResponseDto.builder()
                        .status(200)
                        .result("Başarılı bir şekilde kaydedildi")
                .build());
    }

    @GetMapping(FINDALL)
    @CrossOrigin("*")
    public ResponseEntity<List<User>> findAll(String token){
        return ResponseEntity.ok(userService.findAll(token));
    }
}
