package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{username}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User fetchedUser = userRepository.findByUsername(username);
        if (fetchedUser != null) {
            return new ResponseEntity<>(fetchedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-user")
    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("/sign-in-user")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> signInUser(@RequestBody FetchStoredUserRequest request) throws RequestValidationException {
        User user = FetchStoredUserCommand.execute(request, userRepository);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
