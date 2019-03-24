package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FetchStoredUserCommandTest {

    private final String USERNAME = "mytestuser";
    private final String PASSWORD = "mytestpassword";

    @Mock
    UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        testUser = new User();
        testUser.setUsername(USERNAME);
        testUser.setPassword(UserUtils.getHashedPassword(PASSWORD));
    }

    @Test
    public void shouldFetchUserWithMatchingUsernameAndPassword() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(testUser);
        User fetchedUser = FetchStoredUserCommand.execute(USERNAME, PASSWORD, userRepository);
        verify(userRepository, times(1)).findByUsername(USERNAME);
        assertTrue(fetchedUser.getUsername().equals(USERNAME));
    }

    @Test
    public void shouldNotFetchUserWhenPasswordIsIncorrect() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(testUser);
        User fetchedUser = FetchStoredUserCommand.execute(USERNAME, "incorrectPassword", userRepository);
        verify(userRepository, times(1)).findByUsername(USERNAME);
        assertNull(fetchedUser);
    }

    @Test
    public void shouldThrowExceptionWhenNoUsernameSpecified() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        assertThrows(RequestValidationException.class, () ->
                FetchStoredUserCommand.execute(null, PASSWORD, userRepository));
        verify(userRepository, times(0)).findByUsername(USERNAME);
    }

    @Test
    public void shouldThrowExceptionWhenNoPasswordSpecified() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        assertThrows(RequestValidationException.class, () ->
                FetchStoredUserCommand.execute(USERNAME, null, userRepository));
        verify(userRepository, times(0)).findByUsername(USERNAME);
    }
}