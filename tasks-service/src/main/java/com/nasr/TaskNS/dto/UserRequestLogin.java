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
//    private Long id;
    private String username; // here we use the email as value
    private String email; // CHANGED
    private String password;
}
