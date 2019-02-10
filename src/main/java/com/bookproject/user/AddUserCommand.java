package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import com.bookproject.misc.Country;
import org.apache.commons.validator.routines.EmailValidator;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

class AddUserCommand {

    private AddUserCommand(){}

    static User execute(AddUserRequest request, UserRepository repository) throws RequestValidationException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        validate(request, repository);
        User user = new User(request.username, request.firstName, request.lastName,
                getHashedPassword(request.getPassword()), Country.valueOf(request.getCountry().toUpperCase()),
                request.getEmailAddress());
        repository.save(user);
        return user;
    }

    private static String getHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Arrays.toString(hash);
    }

    private static void validate(AddUserRequest request, UserRepository repository) throws RequestValidationException {
        if (Arrays.stream(Country.values()).noneMatch(t -> t.name().equalsIgnoreCase(request.getCountry()))) {
            throw new RequestValidationException("Invalid country submitted : "+request.getCountry());
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
