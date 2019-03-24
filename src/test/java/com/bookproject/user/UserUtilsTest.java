package com.bookproject.user;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUtilsTest {

    private String PASSWORD = "p@$$wOrD1234";

    @Test
    public void shouldGetIdenticalHashedPasswordForIdenticalInput() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hashedPassword1 = UserUtils.getHashedPassword(PASSWORD);
        String hashedPassword2 = UserUtils.getHashedPassword(PASSWORD);
        assertEquals(hashedPassword1, hashedPassword2);
    }
}