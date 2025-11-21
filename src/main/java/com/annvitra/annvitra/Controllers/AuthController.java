package com.annvitra.annvitra.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.annvitra.annvitra.Services.AuthService;
import com.annvitra.annvitra.DTO.CommonDTO;

import com.annvitra.annvitra.DTO.LoginRequestDTO;
import com.annvitra.annvitra.DTO.LoginResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    // user
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody CommonDTO commonDTO) {
        // Signup logic to be implemented
        try {
            authService.signup(commonDTO);
            return ResponseEntity.ok("Signup successful");
        } catch (Exception e) {
            logger.error("Signup failed", e);
            // If it's an illegal argument (bad input) return 400
            if (e instanceof IllegalArgumentException) {
                return ResponseEntity.badRequest().body("Signup failed: " + e.getMessage());
            }
            return ResponseEntity.status(500).body("Signup failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.login(loginRequest);
            logger.info("User logged in successfully with email: " + loginRequest.getEmail());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            logger.error("Authentication failed for email: " + loginRequest.getEmail(), ex);
            return ResponseEntity.status(401)
                    .body(java.util.Map.of("error", "Authentication failed", "message", ex.getMessage()));
        }
    }

}
