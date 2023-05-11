package com.nasr.TaskNS.services;

import com.nasr.TaskNS.dto.AuthenticationResponse;
import com.nasr.TaskNS.dto.UserRequestLogin;
import com.nasr.TaskNS.dto.UserRequestRegister;
import com.nasr.TaskNS.dto.UserResponse;
import com.nasr.TaskNS.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // before security
    UserResponse register(UserRequestRegister userRequest);

    AuthenticationResponse loginUser(UserRequestLogin userRequest);

   Users getUserByEmail(String email);

    UserResponse findUserById(Long id);

    String findNameOfUserById(Long id);

    List<Users> findAll();

    // after security
//    UserResponse register(UserRequestRegister userRequest);
//
//    AuthenticationResponse loginUser(UserRequestLogin userRequest);


}
