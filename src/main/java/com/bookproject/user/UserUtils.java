package com.bookproject.user;

import com.bookproject.exception.InvalidUserDataException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class UserUtils {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void processSignedUpUser(User user) throws InvalidUserDataException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
