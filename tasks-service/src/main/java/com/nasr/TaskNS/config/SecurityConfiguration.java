//package com.nasr.TaskNS.config;
//
//import jakarta.servlet.Filter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//
//    private final AuthenticationProvider authenticationProvider;
//    private final JwtAuthenticationFilter jwtAuthFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/user/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////                .and()
////                .authenticationProvider(authenticationProvider)
//
//
//
//        return http.build();
//    }
//
//}
