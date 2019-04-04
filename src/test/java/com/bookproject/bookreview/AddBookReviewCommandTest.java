package com.bookproject.bookreview;

import com.bookproject.user.User;
import com.bookproject.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AddBookReviewCommandTest {

    @Mock
    UserRepository userRepository;

    private User testUser;

    private AddBookReviewRequest addBookReviewRequest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        testUser = new User();
        createRequest();
    }

    @Test
    void shouldAddBookReviewGivenValidRequest() {
        when(userRepository.findUserById(addBookReviewRequest.getUserId())).thenReturn(testUser);
        BookReview review = AddBookReviewCommand.execute(addBookReviewRequest, userRepository);

        assertNotNull(review);
        assertEquals(testUser, review.getUser());
    }

    private void createRequest() {
        addBookReviewRequest = new AddBookReviewRequest();
        addBookReviewRequest.setReview("This is a test review");
        addBookReviewRequest.setTitle("Dune");
        addBookReviewRequest.setUserId(1L);
        addBookReviewRequest.setStars(6);
    }


}