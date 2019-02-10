package com.bookproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/add-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest request) {
        try {
            User user = AddUserCommand.execute(request, userRepository);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.INFO, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
