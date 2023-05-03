package com.nasr.TaskNS.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthenticationResponse(String token) {

        this.accessToken = token;
    }
}
