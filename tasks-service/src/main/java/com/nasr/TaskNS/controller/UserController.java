package com.nasr.TaskNS.controller;

//import com.nasr.TaskNS.config.AuthenticationService;

import com.nasr.TaskNS.dto.*;
import com.nasr.TaskNS.mapper.UserResponseMapper;
import com.nasr.TaskNS.repository.UserRepository;
//import com.nasr.TaskNS.security.JWTGenerator;
import com.nasr.TaskNS.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/user")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(value = {"*"})
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;
    private final UserResponseMapper userResponseMapper;


    // before security
//    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
//    public UserResponse register(@RequestBody UserRequestRegister userRequest) {
//        return userService.register(userRequest);
//    }
//
//    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public UserResponse loginUser(@RequestBody UserRequestLogin userRequest) {
//        return userService.loginUser(userRequest);
//    }


//    public UserController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }


    // after security
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserRequestRegister registerDto) {
//        System.out.println(registerDto);
//        try {
//
//            userService.register(registerDto);
//            return new ResponseEntity<String>("User registered successfully", HttpStatus.OK);
//        } catch (HttpServerErrorException.InternalServerError ex) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequestRegister registerDto) {
        System.out.println(registerDto);
        UserResponse registered = userService.register(registerDto);

        return new ResponseEntity<>(registered,HttpStatus.OK);
    }


//    @PostMapping("/register")
//    public UserResponse register(@RequestBody UserRequestRegister registerDto) {
//
//        return userService.register(registerDto);
//
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserRequestLogin loginDto) {
        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }


}
