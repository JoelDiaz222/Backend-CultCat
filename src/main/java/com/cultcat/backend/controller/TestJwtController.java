package com.cultcat.backend.controller;

import com.cultcat.backend.config.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestJwtController {

    // Endpoint to test JWT token generation
    @PostMapping("/generate-token")
    public String generateToken(@RequestParam String googleId, @RequestParam String email) {
        return JwtTokenUtil.generateToken(googleId, email);
    }

    // Endpoint to validate JWT token
    @PostMapping("/validate-token")
    public String validateToken(@RequestParam String token, @RequestParam String googleId) {
        boolean isValid = JwtTokenUtil.validateToken(token, googleId);
        return isValid ? "Token is valid" : "Token is invalid";
    }
}
