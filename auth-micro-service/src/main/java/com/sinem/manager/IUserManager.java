package com.sinem.manager;

import com.sinem.dto.request.UserSaveRequestDto;
import com.sinem.dto.response.UserSaveResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sinem.constants.RestApis.SAVE;

@FeignClient(name = "user-micro-service", url = "${my-project.user-service.url}", dismiss404 = true)
public interface IUserManager {
    @PostMapping(SAVE)
    ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto);
}
