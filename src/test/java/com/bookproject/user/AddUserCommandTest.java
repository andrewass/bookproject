package com.bookproject.user;

import com.bookproject.exception.RequestValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.bookproject.user.AddUserCommand.execute;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddUserCommandTest {

    @Mock
    private UserRepository userRepository;

    private AddUserRequest addUserRequest;

    @BeforeEach
    void init() {
        initMocks(this);
        createUserRequest();
    }

    @Test
    public void shouldSaveUserWhenRequestDataIsValid() throws NoSuchAlgorithmException, RequestValidationException,
            InvalidKeySpecException {

        User addedUser = execute(addUserRequest, userRepository);
        assertNotNull(addedUser);
    }

    @Test
    public void shouldThrowExceptionWhenCountryIsInvalid() {
        final String INVALID_COUNTRY = "UTOPIA";
        addUserRequest.setCountry(INVALID_COUNTRY);
        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addUserRequest, userRepository));

        assertTrue(exception.getMessage().equals("Invalid country submitted : " + INVALID_COUNTRY));
    }

    @Test
    public void shouldThrowExceptionWhenUserIsAlreadyStored() {
        when(userRepository.findByUsername(any(String.class))).thenReturn(new User());

        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addUserRequest, userRepository));

        assertTrue(exception.getMessage().equals("Username already exists : " + addUserRequest.getUsername()));
    }

    @Test
    public void shouldThrowExceptionWhenEmailAddressIsInvalid() {
        final String INVALID_EMAIL = "testmail$gmail.com";
        addUserRequest.setEmailAddress(INVALID_EMAIL);
        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addUserRequest, userRepository));

        assertTrue(exception.getMessage().equals("Invalid emailAddress address submitted : " + INVALID_EMAIL));
    }

    @Test
    public void shouldThrowExceptionWhenEmailAddressIsAlreadyStored() {
        when(userRepository.findByEmailAddress(any(String.class))).thenReturn(new User());

        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addUserRequest, userRepository));

        assertTrue(exception.getMessage().equals("Submitted emailAddress address already registered : " + addUserRequest.getEmailAddress()));
    }

    private void createUserRequest() {
        addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("testuser");
        addUserRequest.setFirstName("John");
        addUserRequest.setLastName("Doe");
        addUserRequest.setPassword("p@$$wOrD1234");
        addUserRequest.setCountry("NORWAY");
        addUserRequest.setEmailAddress("testmail@gmail.com");
    }
}