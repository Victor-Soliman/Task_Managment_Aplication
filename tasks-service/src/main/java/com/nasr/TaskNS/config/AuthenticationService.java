//package com.nasr.TaskNS.config;
//
//import com.nasr.TaskNS.dto.*;
//import com.nasr.TaskNS.entity.Role;
//import com.nasr.TaskNS.entity.User;
//import com.nasr.TaskNS.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(UserRequestRegister userRequest) {
//        var user = User.builder()
//                .userName(userRequest.getUserName())
//                .email(userRequest.getEmail())
//                .userPassword(passwordEncoder.encode(userRequest.getPassword()))
//                .role(Role.USER)
//                .build();
//
//        userRepository.save(user);
//
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//
////    private boolean checkMatchedPassword(AuthenticationRequest userRequest) {
////        if (userRepository.existsUserByEmail(userRequest.getEmail())) {
////            String cryptedPassword = userRepository.findByEmail(userRequest.getEmail()).get().getUserPassword();
////            return passwordEncoder.matches(userRequest.getPassword(), cryptedPassword);
////        }
////        return false;
////    }
//
//
//    public AuthenticationResponse authenticate(AuthenticationRequest userRequest) throws Exception {
////        if (checkMatchedPassword(userRequest)) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            userRequest.getEmail(),
//                            userRequest.getPassword()
//                    )
//            );
//            var user = userRepository.findByEmail(userRequest.getEmail())
//                    .orElseThrow();
//            var jwtToken = jwtService.generateToken(user);
//            return AuthenticationResponse.builder()
//                    .token(jwtToken)
//                    .build();
//        }
//
////
////        throw new Exception();
////    }
//}
