package com.nasr.TaskNS.mapper;

import com.nasr.TaskNS.dto.UserRequestRegister;
import com.nasr.TaskNS.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public Users fromRegisterDtoToEntity(UserRequestRegister userRequest){
        return Users.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .email(userRequest.getEmail())
                .build();
    }
}
