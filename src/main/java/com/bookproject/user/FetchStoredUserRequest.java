package com.bookproject.user;

import lombok.Data;

@Data
public class FetchStoredUserRequest{

    private String username;

    private String password;
}
