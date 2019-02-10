package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import com.bookproject.misc.Country;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Arrays;

public class AddUserCommand {


    static void execute(AddUserRequest request, UserRepository repository) throws RequestValidationException {
        validate(request, repository);


    }

    private static void validate(AddUserRequest request, UserRepository repository) throws RequestValidationException {
        if (request.getCountry() != null)
            if (!Arrays.stream(Country.values()).anyMatch((t) -> t.name().equalsIgnoreCase(request.getCountry()))) {
                throw new RequestValidationException("Invalid country submitted");
            }
        if (repository.findByUsername(request.getUsername().toLowerCase()) != null) {
            throw new RequestValidationException("Username already exists");
        }
        if (!EmailValidator.getInstance().isValid(request.getEmail())) {
            throw new RequestValidationException("Invalid email address submitted");
        }
        if (repository.findByEmail(request.getEmail()) != null) {
            throw new RequestValidationException("Submitted email address already registered");
        }
    }
}






