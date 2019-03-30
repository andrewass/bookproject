package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;

import static com.bookproject.user.UserUtils.getHashedPassword;

class FetchStoredUserCommand {

    private FetchStoredUserCommand() {
    }

    static User execute(FetchStoredUserRequest request, UserRepository repository)
            throws RequestValidationException {
        validate(request.getUsername(), request.getPassword());
        User retrievedUser = repository.findByUsername(request.getUsername());
        if (retrievedUser == null ||
                !retrievedUser.getPassword().equals(getHashedPassword(request.getPassword()))) {
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
