package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in-user")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> signInUser(@RequestBody SignInRequest request)  {
        User user = null;
        try {
            user = FetchStoredUserCommand.execute(request, userRepository);
            if(user != null){
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RequestValidationException e) {
            log.error("Request Validation Exception "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
