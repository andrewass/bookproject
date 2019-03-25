package com.bookproject.user;

import com.bookproject.book.Book;
import com.bookproject.book.BookBuilder;
import com.bookproject.book.BookCondition;
import com.bookproject.misc.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private User testuser;

    @BeforeEach
    public void init() {
        initMocks(this);
        createUser();
    }

    @Test
    public void shouldFindUserForGivenUsername() throws Exception {
        when(userRepository.findByUsername(testuser.getUsername())).thenReturn(testuser);

        mockMvc.perform(get("/user/" + testuser.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(testuser.getUsername())))
                .andExpect(jsonPath("$.emailAddress", is(testuser.getEmailAddress())))
                .andExpect(jsonPath("$.residingCountry", is(testuser.getResidingCountry().name())))
                .andExpect(jsonPath("$.bookList[0].title", is(testuser.getBookList().get(0).getTitle())))
                .andExpect(jsonPath("$.bookList[0].condition",
                        is(testuser.getBookList().get(0).getCondition().name())));
    }

    private void createUser() {
        testuser = new User();
        List<Book> booklist = new ArrayList<>();
        booklist.add(new BookBuilder().withTitle("Dune")
                .withCondition(BookCondition.MINT).build());
        testuser.setBookList(booklist);
        testuser.setUsername("randomUser");
        testuser.setEmailAddress("testmail@gmail.com");
        testuser.setResidingCountry(Country.NORWAY);
    }

    private String getExpectedResult() {
        return null;
    }

}