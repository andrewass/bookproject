package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class FetchStoredUserCommandTest {

    private final String USERNAME = "mytestuser";
    private final String PASSWORD = "mytestpassword";
    private final String INCORRECT_PASSWORD = "p@$$wOrD1234";

    @Mock
    UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        initMocks(this);
        testUser = new User();
        testUser.setUsername(USERNAME);
        testUser.setPassword(UserUtils.getHashedPassword(PASSWORD));
    }

    @Test
    void shouldFetchUserWithMatchingUsernameAndPassword() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(testUser);
        User fetchedUser = FetchStoredUserCommand.execute(new SignInRequest(USERNAME, PASSWORD), userRepository);
        verify(userRepository, times(1)).findByUsername(USERNAME);
        assertTrue(fetchedUser.getUsername().equals(USERNAME));
    }

    @Test
    void shouldNotFetchUserWhenPasswordIsIncorrect() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(testUser);
        User fetchedUser = FetchStoredUserCommand.execute(new SignInRequest(USERNAME, INCORRECT_PASSWORD),
                userRepository);
        verify(userRepository, times(1)).findByUsername(USERNAME);
        assertNull(fetchedUser);
    }

    @Test
    void shouldThrowExceptionWhenNoUsernameSpecified() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        assertThrows(RequestValidationException.class, () ->
                FetchStoredUserCommand.execute(new SignInRequest(null, PASSWORD), userRepository));
        verify(userRepository, times(0)).findByUsername(USERNAME);
    }

    @Test
    void shouldThrowExceptionWhenNoPasswordSpecified() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {
        when(userRepository.findByUsername(USERNAME)).thenReturn(null);
        assertThrows(RequestValidationException.class, () ->
                FetchStoredUserCommand.execute(new SignInRequest(USERNAME, null), userRepository));
        verify(userRepository, times(0)).findByUsername(USERNAME);
    }
}