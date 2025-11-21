package com.annvitra.annvitra.DTO;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
    private String role;
}
