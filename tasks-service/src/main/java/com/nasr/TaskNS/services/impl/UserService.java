package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.*;
import com.nasr.TaskNS.entity.Role;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.mapper.UserRequestMapper;
import com.nasr.TaskNS.mapper.UserResponseMapper;
import com.nasr.TaskNS.repository.RoleRepository;
import com.nasr.TaskNS.repository.UserRepository;
import com.nasr.TaskNS.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public ResponseEntity<MessageResponse> register(UserRequestRegister registerDto) {
            if (checkEmailExists(registerDto.getEmail())){
                return new ResponseEntity<>(new MessageResponse("Email already exists"),HttpStatus.BAD_REQUEST);
            }

        Users user = userRequestMapper.fromRegisterDtoToEntity(registerDto);

        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>(new MessageResponse("Registered with success"),HttpStatus.OK);

    }

    @Override
    public AuthenticationResponse loginUser(UserRequestLogin loginDto) {
        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        System.out.println(getUserByEmail(loginDto.getEmail()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new AuthenticationResponse(token);
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email " + email));
    }

    @Override
    public UserResponse findUserById(Long id) {
        Users userFromDB = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found by id: " + id));
        return userResponseMapper.fromEntityToDtoRegister(userFromDB);
    }

    @Override
    public String findNameOfUserById(Long id) {
        return userRepository.findNameOfUserById(id);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean checkEmailExists(String email) {
        System.out.println(userRepository.existsByEmail(email));
        return userRepository.existsByEmail(email);
    }

}
