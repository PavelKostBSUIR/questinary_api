package com.softarex.questinary.entity;

import lombok.Data;

@Data
public class JWTRequest {

    private String login;
    private String password;
}
