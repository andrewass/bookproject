package com.bookproject.bookreview;

import com.bookproject.user.User;
import com.bookproject.user.UserRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookReviewController.class)
class BookReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookReviewRepository bookReviewRepository;

    private User testUser;

    @BeforeEach
    void init() {
        initMocks(this);
        createTestUser();
    }

    @Test
    void shouldFindBookReviews() throws Exception {
        when(bookReviewRepository.findAllBookReviewsWithTitle("dune"))
                .thenReturn(Collections.singletonList(new BookReview()));

        mockMvc.perform(get("/book-review/dune")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnStatusNotFoundWhenNoReviewsAreFound() throws Exception {
        when(bookReviewRepository.findAllBookReviewsWithTitle("dune")).thenReturn(emptyList());

        mockMvc.perform(get("/book-review/dune")
                .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddBookReviewAndReturnStatusOk() throws Exception {
        when(userRepository.findByUsername(testUser.getUsername())).thenReturn(testUser);
        mockMvc.perform(
                post("/add-book-review")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private String createRequestBody() {
        Gson gson = new Gson();
        return gson.toJson(testUser);
    }

    private void createTestUser() {
        testUser = new User();
    }


}