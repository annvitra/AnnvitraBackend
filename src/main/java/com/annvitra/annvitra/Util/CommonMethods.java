package com.annvitra.annvitra.Util;

import org.springframework.stereotype.Service;

@Service
public class CommonMethods {

    public String generateOTP() {
    java.security.SecureRandom random = new java.security.SecureRandom();
    String otp = String.valueOf(100000 + random.nextInt(900000));
    // use otp as needed, e.g. log or return
    
    System.out.println("Generated OTP: " + otp);
    return otp;
    }

    public Boolean isOTPExpired(java.time.LocalDateTime otpExpiryTime) {
        java.time.LocalDateTime currentTime = java.time.LocalDateTime.now();
        return currentTime.isAfter(otpExpiryTime);
    }

    public Boolean verifyOTP(String sentOTP, String receivedOTP) {
        return sentOTP.equals(receivedOTP);
    }
}
