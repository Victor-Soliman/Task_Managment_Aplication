package com.nasr.TaskNS.services;

import com.nasr.TaskNS.dto.*;
import com.nasr.TaskNS.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    // before security
    ResponseEntity<MessageResponse> register(UserRequestRegister userRequest);

    AuthenticationResponse loginUser(UserRequestLogin userRequest);

   Users getUserByEmail(String email);

    UserResponse findUserById(Long id);

    String findNameOfUserById(Long id);

    List<Users> findAll();

    Boolean checkEmailExists(String email);

}
