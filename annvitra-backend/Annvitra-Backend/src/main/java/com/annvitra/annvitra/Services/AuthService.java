package com.annvitra.annvitra.Services;

import com.annvitra.annvitra.DTO.CommonDTO;
import com.annvitra.annvitra.DTO.CommonDTO;
import com.annvitra.annvitra.DTO.LoginRequestDTO;
import com.annvitra.annvitra.DTO.LoginResponseDTO;

public interface AuthService {

    // User
    public void signup(CommonDTO commonDTO);
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    



}
