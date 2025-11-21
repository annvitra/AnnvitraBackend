package com.annvitra.annvitra.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String email;
    private String message;
    private String role;
}
