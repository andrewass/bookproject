package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.bookproject.user.UserUtils.getHashedPassword;

public class FetchStoredUserCommand {

    private FetchStoredUserCommand() {
    }

    static User execute(String username, String password, UserRepository repository) throws RequestValidationException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        validate(username, password);
        User retrievedUser = repository.findByUsername(username);
        if (retrievedUser != null && !retrievedUser.getPassword().equals(getHashedPassword(password))) {
            return null;
        }
        return retrievedUser;
    }

    private static void validate(String username, String password) throws RequestValidationException {
        if (username == null) {
            throw new RequestValidationException("Username not specified during User retrieval");
        }
        if (password == null) {
            throw new RequestValidationException("Password not specified during User retrieval");
        }
    }
}
