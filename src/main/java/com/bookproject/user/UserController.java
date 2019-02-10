package com.bookproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/add-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public void registerBook(@RequestBody AddUserRequest request) {

    }



}
