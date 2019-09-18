package com.bookproject.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockedUsers {

    MockedUsers() {
        int t = 3;
    }

}

