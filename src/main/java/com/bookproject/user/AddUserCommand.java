package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import com.bookproject.misc.Country;
import org.apache.commons.validator.routines.EmailValidator;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.bookproject.user.UserUtils.getHashedPassword;

class AddUserCommand {

    private AddUserCommand() { }

    static User execute(AddUserRequest request, UserRepository repository) throws RequestValidationException,
            NoSuchAlgorithmException {
        validate(request, repository);
        return new User(request.getUsername(), request.getFirstName(), request.getLastName(),
                getHashedPassword(request.getPassword()),
                Country.valueOf(request.getCountry().toUpperCase()),
                request.getEmailAddress());
    }

    private static void validate(AddUserRequest request, UserRepository repository) throws RequestValidationException {
        if (Arrays.stream(Country.values()).noneMatch(t -> t.name().equalsIgnoreCase(request.getCountry()))) {
            throw new RequestValidationException("Invalid country submitted : " + request.getCountry());
        }
        if (repository.findByUsername(request.getUsername()) != null) {
            throw new RequestValidationException("Username already exists : "+request.getUsername());
        }
        if (!EmailValidator.getInstance().isValid(request.getEmailAddress())) {
            throw new RequestValidationException("Invalid emailAddress address submitted : "+request.getEmailAddress());
        }
        if (repository.findByEmailAddress(request.getEmailAddress()) != null) {
            throw new RequestValidationException("Submitted emailAddress address already registered : "+request.getEmailAddress());
        }
    }
}
