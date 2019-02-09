package com.bookproject.user;

import lombok.Data;

@Data
public class AddUserRequest {

    String firstName;

    String lastName;

    String email;

    String password;

    String country;
}
