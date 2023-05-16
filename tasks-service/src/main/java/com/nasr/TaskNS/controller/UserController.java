package com.nasr.TaskNS.controller;

import com.nasr.TaskNS.dto.*;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/user")
@CrossOrigin(value = {"*"})
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody UserRequestRegister registerDto) {
        return userService.register(registerDto);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserRequestLogin loginDto) {
        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<String> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findNameOfUserById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

}
