package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.AuthenticationResponse;
import com.nasr.TaskNS.dto.UserRequestLogin;
import com.nasr.TaskNS.dto.UserRequestRegister;
import com.nasr.TaskNS.dto.UserResponse;
import com.nasr.TaskNS.entity.Role;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.mapper.UserRequestMapper;
import com.nasr.TaskNS.mapper.UserResponseMapper;
import com.nasr.TaskNS.repository.RoleRepository;
import com.nasr.TaskNS.repository.UserRepository;
import com.nasr.TaskNS.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements com.nasr.TaskNS.services.UserService {


    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;


    // before security
//    @Override
//    public UserResponse register(UserRequestRegister registerDto) {
//        Users user = userRequestMapper.fromRegisterDtoToEntity(registerDto);
//        Users savedUsers = userRepository.save(user);
//        log.info("User Successfully saved in DB");
//        return userResponseMapper.fromEntityToDtoRegister(savedUsers);
//    }
//
//    @Override
//    public UserResponse loginUser(UserRequestLogin userRequest) {
//        System.out.println(userRequest);
//        Users UserByUsernameAndPassword = userRepository.findByUsernameAndPassword(
//                userRequest.getUsername(),userRequest.getPassword()).orElseThrow(
//                () -> new EntityExistsException("user not found in the database"));
//        return userResponseMapper.fromEntityToDtoLogIn(UserByUsernameAndPassword);
//    }


    @Override
    public UserResponse register(UserRequestRegister registerDto) {
        // check if it is in the db

//  if (userRepository.existsByUsername(registerDto.getUsername())) {
//                return new ResponseEntity<String>("Username is taken!", HttpStatus.BAD_REQUEST);
//            }

        // if it is not set the name & password
//        Users user = new Users();
        Users user = userRequestMapper.fromRegisterDtoToEntity(registerDto);
//        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // set roles : if you want to make some admin -> you add logic here


        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles)); // this is the that you set roles


        Users savedUser = userRepository.save(user);

        // in case i want to crate jwt
        return userResponseMapper.fromEntityToDtoRegister(savedUser);

    }

    @Override
    public AuthenticationResponse loginUser(UserRequestLogin loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

//        return new ResponseEntity<>("User signedIn Success", HttpStatus.OK);
        return new AuthenticationResponse(token);
    }

    public Users getUserByEmail(String email){
       return userRepository.findByEmail(email);
    }

}
