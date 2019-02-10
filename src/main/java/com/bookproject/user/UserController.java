package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
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
    public void addUser(@RequestBody AddUserRequest request) {
        try{
            AddUserCommand.execute(request, userRepository);
        }
        catch (RequestValidationException rve){
            System.out.println(rve.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
