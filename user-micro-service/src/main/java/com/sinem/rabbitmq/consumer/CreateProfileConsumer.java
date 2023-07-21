package com.sinem.rabbitmq.consumer;

import com.sinem.dto.request.UserSaveRequestDto;
import com.sinem.rabbitmq.model.CreateProfile;
import com.sinem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProfileConsumer {
    private final UserService userService;
    @RabbitListener(queues = "queue-auth-create-profile")
    public void consumerCreateProfile(CreateProfile createProfile){
        System.out.println("Create Profile: " + createProfile.toString());
        userService.save(UserSaveRequestDto.builder()
                .authid(createProfile.getAuthId())
                .username(createProfile.getUsername())
                .email(createProfile.getEmail())
                .build());

    }
}
