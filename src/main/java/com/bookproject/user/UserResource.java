package com.bookproject.user;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class UserResource extends ResourceSupport {

    private String username;
    private String emailAddress;

    UserResource(User user){
        username = user.getUsername();
        emailAddress = user.getEmailAddress();
    }

}
