package com.alejandromax.token.controller;

import com.alejandromax.token.dto.LoginRequest;
import com.alejandromax.token.dto.TokenResponse;
import com.alejandromax.token.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final JwtService jwtService;

    public AuthApiController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@Valid @RequestBody LoginRequest request) {

        if (!"admin".equals(request.getUsername()) || !"1234".equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Incorrect Credentials");
        }

        String token = jwtService.generateToken(request.getUsername());

        return ResponseEntity.ok(new TokenResponse(token));
    }
}