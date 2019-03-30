package com.bookproject.user;

import lombok.Data;

@Data
public class FetchStoredUserRequest {

    private String username;

    private String password;

    FetchStoredUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public FetchStoredUserRequest(){}
}
