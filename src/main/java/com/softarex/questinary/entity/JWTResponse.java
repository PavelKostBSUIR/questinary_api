package com.softarex.questinary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {

    //todo where is it used?
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
