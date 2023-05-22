package com.nasr.TaskNS.services.impl;

import com.nasr.TaskNS.dto.*;
import com.nasr.TaskNS.entity.Role;
import com.nasr.TaskNS.entity.Tasks;
import com.nasr.TaskNS.entity.Users;
import com.nasr.TaskNS.mapper.UserRequestMapper;
import com.nasr.TaskNS.mapper.UserResponseMapper;
import com.nasr.TaskNS.repository.RoleRepository;
import com.nasr.TaskNS.repository.UserRepository;
import com.nasr.TaskNS.security.JWTGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRequestMapper userRequestMapper;
    @Mock
    private UserResponseMapper userResponseMapper;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTGenerator jwtGenerator;
    @Mock
    private MessageResponse messageResponse;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void register() {
        Users testUser = new Users();
        Role testRole = new Role();
        List<Role> testRolesList = new ArrayList<>();
        testRolesList.add(testRole);
        UserRequestRegister testUserRequestRegister = new UserRequestRegister();


        Mockito.when(userService.checkEmailExists(testUserRequestRegister.getEmail())).thenReturn(false);
        Mockito.when(userRequestMapper.fromRegisterDtoToEntity(testUserRequestRegister)).thenReturn(testUser);
        Mockito.when(roleRepository.findByName("USER")).thenReturn(Optional.of(testRole));
        Mockito.when(userRepository.save(any(Users.class))).thenReturn(testUser);

        ResponseEntity<MessageResponse> actual = userService.register(testUserRequestRegister);

        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
        Assertions.assertEquals("Registered with success", actual.getBody().getMsg());

        Mockito.verify(userRequestMapper, Mockito.times(1)).fromRegisterDtoToEntity(testUserRequestRegister);
        Mockito.verify(roleRepository, Mockito.times(1)).findByName("USER");
        Mockito.verify(userRepository, Mockito.times(1)).save(any(Users.class));

    }

    @Test
    void loginUser() {
        Authentication authentication = Mockito.mock(Authentication.class);
        // 1
        UserRequestLogin userRequestLogin = new UserRequestLogin();
        userRequestLogin.setEmail("email");
        // 2
        String email = userRequestLogin.getEmail();
        Users user = new Users();
        user.setEmail(email);
        //3
        String token = "test_token";

        // 1*
        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        // 2*
//        Mockito.when(userService.getUserByEmail(email)).thenReturn(user);

        // 3*
        Mockito.when(jwtGenerator.generateToken(authentication)).thenReturn(token);

        // 4 ...
        AuthenticationResponse response = userService.loginUser(userRequestLogin);

        Assertions.assertEquals(token,response.getAccessToken());

        Mockito.verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class
        ));
//        Mockito.verify(userService.getUserByEmail(userRequestLogin.getEmail()));
        Mockito.verify(jwtGenerator).generateToken(authentication);
    }

    @Test
    void getUserByEmail() {
        String testEmail = "test@email.com";
        Users testUser = new Users();
        testUser.setEmail(testEmail);

        Mockito.when(userRepository.findByEmail(testEmail)).thenReturn(Optional.of(testUser));

        Users actual = userService.getUserByEmail(testEmail);

        Assertions.assertEquals(testUser, actual);

        Mockito.verify(userRepository).findByEmail(testEmail);

    }

    @Test
    void findUserById() {
        Long testUserId = 1L;
        Users userFromDB = new Users();
        UserResponse testUserResponse = new UserResponse();

        Mockito.when(userRepository.findById(testUserId)).thenReturn(Optional.of(userFromDB));
        Mockito.when(userResponseMapper.fromEntityToDtoRegister(userFromDB)).thenReturn(testUserResponse);

        UserResponse userResponse = userService.findUserById(testUserId);

        Assertions.assertEquals(testUserResponse, userResponse);
        Mockito.verify(userRepository).findById(testUserId);
    }

    @Test
    void findNameOfUserById() {
        Long testId = 1L;
        String testNameOfUser = "username";

        Mockito.when(userRepository.findNameOfUserById(testId)).thenReturn(testNameOfUser);

        String actual = userService.findNameOfUserById(testId);

        Assertions.assertEquals(testNameOfUser, actual);

        Mockito.verify(userRepository, Mockito.times(1)).findNameOfUserById(testId);
    }

    @Test
    void findAll() {
        Users testUser1 = new Users();
        Users testUser2 = new Users();
        List<Users> expectedUsers = new ArrayList<>();
        expectedUsers.add(testUser1);
        expectedUsers.add(testUser2);

        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

        List<Users> actual = userService.findAll();

        Assertions.assertEquals(expectedUsers, actual);

        Mockito.verify(userRepository, Mockito.times(1)).findAll();


    }

    @Test
    void checkEmailExists() {
        String testEmail = "email";
        Boolean exists = true;

        Mockito.when(userRepository.existsByEmail(testEmail)).thenReturn(exists);

        userService.checkEmailExists(testEmail);

        Assertions.assertTrue(exists);

        Mockito.verify(userRepository).existsByEmail(testEmail);

    }
}