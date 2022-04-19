package com.softarex.questinary.service;

import com.softarex.questinary.entity.JWTAuthentication;
import com.softarex.questinary.entity.JWTRequest;
import com.softarex.questinary.entity.JWTResponse;
import com.softarex.questinary.entity.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//todo what is AuthException and why I use BadCredentialsException?
@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTProvider jwtProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public JWTResponse login(@NonNull JWTRequest authRequest) {
        User user = userService.getByLogin(authRequest.getLogin());
        if (user == null) {
            throw new BadCredentialsException("Unknown user login " + authRequest.getLogin());
        }
        if (user.getPassword().equals(authRequest.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JWTResponse(accessToken, refreshToken);
        } else {
            throw new BadCredentialsException("Incorrect password");
        }
    }

    /*public JWTResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                User user = userService.getByLogin(login);
                if (user == null) {
                    throw new BadCredentialsException("Unknown user login " + login);
                }
                String accessToken = jwtProvider.generateAccessToken(user);
                return new JWTResponse(accessToken, null);
            }
        }
        return new JWTResponse(null, null);
    }*/

    public JWTResponse refresh(@NonNull String refreshToken) {
        boolean validated = jwtProvider.validateRefreshToken(refreshToken);
        if (validated) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login);
                if (user == null) {
                    throw new BadCredentialsException("Unknown user login " + login);
                }
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JWTResponse(accessToken, newRefreshToken);
            }
        }
        throw new BadCredentialsException("Invalid JWT token");
    }

    public JWTAuthentication getAuthInfo() {
        return (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}

