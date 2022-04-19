package com.softarex.questinary.controller;

import com.softarex.questinary.entity.JWTRequest;
import com.softarex.questinary.entity.JWTResponse;
import com.softarex.questinary.entity.RefreshJWTRequest;
import com.softarex.questinary.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest authRequest) {
        JWTResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTResponse> getNewRefreshToken(@RequestBody RefreshJWTRequest request) {
        final JWTResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}