package com.bookproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User fetchedUser = userRepository.findByUsername(username);
        if(fetchedUser != null){
            return new ResponseEntity<>(fetchedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest request) {
        try {
            User user = AddUserCommand.execute(request, userRepository);
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-in-user")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> signInUser(@RequestBody FetchStoredUserRequest request) {
        try {
            User user = FetchStoredUserCommand.execute(request, userRepository);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
