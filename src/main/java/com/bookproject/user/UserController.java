package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private UserResourceAssembler userResourceAssembler = new UserResourceAssembler();

    @GetMapping("/user/{username}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserResource> getUserByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            ResponseEntity<UserResource> userResourceResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return userResourceResponseEntity;
        }
        UserResource userResource = userResourceAssembler.toResource(user);
        userResource.add(
                linkTo(methodOn(UserController.class).getUserByUsername(username))
                .withSelfRel());
        return new ResponseEntity<>(userResource, HttpStatus.OK);
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
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
