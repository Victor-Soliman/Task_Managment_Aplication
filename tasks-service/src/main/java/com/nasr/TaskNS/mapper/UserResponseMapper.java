package com.nasr.TaskNS.mapper;

import com.nasr.TaskNS.dto.UserResponse;
import com.nasr.TaskNS.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse fromEntityToDtoLogIn(Users users) {
        return UserResponse.builder()
                .id(users.getId())
                .userName(users.getUsername())
                .password(users.getPassword())
                .build();
    }
    public UserResponse fromEntityToDtoRegister(Users users) {
        return UserResponse.builder()
                .id(users.getId())
                .userName(users.getUsername())
                .password(users.getPassword())
                .email(users.getEmail())
                .build();
    }

}
