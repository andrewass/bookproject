package com.bookproject.user;

import lombok.Data;

@Data
public class SignInRequest {

    private String username;

    private String password;

    SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SignInRequest(){}
}
