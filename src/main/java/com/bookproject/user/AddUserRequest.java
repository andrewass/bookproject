package com.bookproject.user;

import lombok.Data;

@Data
class AddUserRequest {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String username;

    private String password;

    private String country;
}
