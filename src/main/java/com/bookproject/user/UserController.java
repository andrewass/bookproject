package com.bookproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            Logger logger = Logger.getLogger(UserController.class.getName());
            logger.log(Level.INFO, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/sign-in-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> signInUser(@RequestParam String username, @RequestParam String password) {
        try {
            User user = FetchStoredUserCommand.execute(username, password, userRepository);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Logger logger = Logger.getLogger(UserController.class.getName());
            logger.log(Level.INFO, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }
}
