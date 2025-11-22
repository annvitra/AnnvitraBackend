package com.annvitra.annvitra.Services;

import com.annvitra.annvitra.DTO.CommonDTO;



public interface AuthService {

    // User
    public void signup(CommonDTO commonDTO);
    // public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    public void verifyMobile(CommonDTO commonDTO);

    public void verifyOTP(CommonDTO commonDTO);

    



}
