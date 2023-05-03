package com.nasr.TaskNS.security;

import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    public static final Long JWT_EXPIRATION = 70000L;
    public static final String JWT_SECRET_KEY = "7234753778214125432A462D4A614E645267556B58703273357638792F423F45";
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
