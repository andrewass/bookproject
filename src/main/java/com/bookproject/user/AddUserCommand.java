package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import com.bookproject.misc.Country;
import org.apache.commons.validator.routines.EmailValidator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import static com.bookproject.user.UserUtils.getHashedPassword;

class AddUserCommand {

    private AddUserCommand() {
    }

    static User execute(AddUserRequest request, UserRepository repository) throws RequestValidationException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        validate(request, repository);
        User user = new User(request.getUsername(), request.getFirstName(), request.getLastName(),
                getHashedPassword(request.getPassword()),
                Country.valueOf(request.getCountry().toUpperCase()),
                request.getEmailAddress());
        repository.save(user);
        return user;
    }

    private static void validate(AddUserRequest request, UserRepository repository) throws RequestValidationException {
        if (Arrays.stream(Country.values()).noneMatch(t -> t.name().equalsIgnoreCase(request.getCountry()))) {
            throw new RequestValidationException("Invalid country submitted : " + request.getCountry());
        }
        if (repository.findByUsername(request.getUsername().toLowerCase()) != null) {
            throw new RequestValidationException("Username already exists");
        }
        if (!EmailValidator.getInstance().isValid(request.getEmailAddress())) {
            throw new RequestValidationException("Invalid emailAddress address submitted");
        }
        if (repository.findByEmailAddress(request.getEmailAddress()) != null) {
            throw new RequestValidationException("Submitted emailAddress address already registered");
        }
    }
}
