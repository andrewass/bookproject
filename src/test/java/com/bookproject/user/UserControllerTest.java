package com.bookproject.user;

import com.bookproject.misc.Country;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private User testUser;

    private User fetchedUser;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void shouldFindUserForGivenUsername() throws Exception {
        createUserToAdd();
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);

        mockMvc.perform(get("/user/" + testUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnStatusNotFoundWhenUserNotExists() throws Exception {
        createUserToAdd();
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(null);

        mockMvc.perform(get("/user/" + testUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddUserFromRequest() throws Exception {
        createUserToAdd();
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(null);

        mockMvc.perform(
                post("/add-user")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFetchPreviouslyStoredUser() throws Exception {
        createUserToFetch();
        createFetchedUser();
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(fetchedUser);

        mockMvc.perform(
                post("/sign-in-user")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnStatusNotFoundWhenGivenNonExistingUser() throws Exception {
        createUserToFetch();
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(null);

        mockMvc.perform(
                post("/sign-in-user")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnStatusBadRequestWhenValidationFailsWhenFetchingUser() throws Exception {
        createUserToFetch();
        testUser.setUsername(null);

        mockMvc.perform(
                post("/sign-in-user")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isBadRequest());
    }

    private String createRequestBody() {
        Gson gson = new Gson();
        return gson.toJson(testUser);
    }

    private void createUserToAdd() {
        testUser = new User();
        testUser.setUsername("randomUser");
        testUser.setEmailAddress("testmail@gmail.com");
        testUser.setPassword("p@$$woRd1234");
        testUser.setCountry(Country.NORWAY);
    }

    private void createUserToFetch() {
        testUser = new User();
        testUser.setUsername("randomUser");
        testUser.setPassword("p@$$woRd1234");
    }

    private void createFetchedUser() {
        fetchedUser = new User();
        fetchedUser.setUsername(testUser.getUsername());
        fetchedUser.setPassword(UserUtils.getHashedPassword(testUser.getPassword()));
    }
}