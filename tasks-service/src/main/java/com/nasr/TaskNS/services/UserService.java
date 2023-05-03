package com.nasr.TaskNS.services;

import com.nasr.TaskNS.dto.AuthenticationResponse;
import com.nasr.TaskNS.dto.UserRequestLogin;
import com.nasr.TaskNS.dto.UserRequestRegister;
import com.nasr.TaskNS.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // before security
    UserResponse register(UserRequestRegister userRequest);

    AuthenticationResponse loginUser(UserRequestLogin userRequest);

    // after security
//    UserResponse register(UserRequestRegister userRequest);
//
//    AuthenticationResponse loginUser(UserRequestLogin userRequest);
}
