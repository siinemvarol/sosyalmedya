package com.sinem.service;

import com.sinem.dto.request.UserSaveRequestDto;
import com.sinem.repository.IUserRepository;
import com.sinem.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    public void save(UserSaveRequestDto dto){
        userRepository.save(User.builder()
                        .authid(dto.getAuthid())
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                .build());
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
