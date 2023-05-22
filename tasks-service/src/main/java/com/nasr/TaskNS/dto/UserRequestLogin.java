package com.nasr.TaskNS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestLogin {
    private String username; // we don't need it
    private String email;
    private String password;
}
