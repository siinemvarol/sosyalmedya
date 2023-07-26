package com.sinem.service;

import com.sinem.dto.request.UserSaveRequestDto;
import com.sinem.exceptions.ErrorType;
import com.sinem.exceptions.UserException;
import com.sinem.repository.IUserRepository;
import com.sinem.repository.entity.User;
import com.sinem.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
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
    public List<User> findAll(String token){
        Optional<Long> id = jwtTokenManager.getByIdFromToken(token);
        if(id.isEmpty()){
            throw new UserException(ErrorType.INVALID_TOKEN);
        }
        return userRepository.findAll();
    }
}
