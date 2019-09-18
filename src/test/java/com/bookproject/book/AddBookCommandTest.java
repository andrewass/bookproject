package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.author.AuthorRepository;
import com.bookproject.exception.RequestValidationException;
import com.bookproject.user.User;
import com.bookproject.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.bookproject.book.BookUtils.execute;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class AddBookCommandTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private UserRepository userRepository;

    private AddBookRequest addBookRequest;

    @BeforeEach
    void init() {
        initMocks(this);
        when(userRepository.findByUsername(any(String.class))).thenReturn(new User());
        when(authorRepository.save(any(Author.class))).thenReturn(new Author());
        createAddBookRequest();
    }

    @Test
    void shouldAddBookWhenRequestContainsValidValues() throws RequestValidationException {
        when(authorRepository.findAuthorByName(any(String.class), any(String.class))).thenReturn(new Author());
        Book book = execute(addBookRequest, authorRepository, userRepository);

        verify(authorRepository, times(0)).save(any(Author.class));
        assertNotNull(book);
        assertNotNull(book.getOwner());
        assertNotNull(book.getAuthor());
    }

    @Test
    void shouldAddBookWithoutUserAndAuthor() throws RequestValidationException {
        addBookRequest.setUsername(null);
        addBookRequest.setAuthorFirstname(null);
        addBookRequest.setAuthorLastname(null);
        Book book = execute(addBookRequest, authorRepository, userRepository);

        assertNotNull(book);
        assertNull(book.getAuthor());
        assertNull(book.getOwner());
    }

    @Test
    void shouldSaveNewAuthorWhenAddingBook() throws RequestValidationException {
        when(authorRepository.findAuthorByName(any(String.class), any(String.class))).thenReturn(null);
        Book book = execute(addBookRequest, authorRepository, userRepository);

        verify(authorRepository, times(1)).save(any(Author.class));
        assertNotNull(book);
        assertNotNull(book.getAuthor());
        assertNotNull(book.getOwner());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsMissing() {
        addBookRequest.setTitle(null);
        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addBookRequest, authorRepository, userRepository));
        assertTrue(exception.getMessage().equals("Title must be specified when adding book"));
    }

    @Test
    void shouldThrowExceptionWhenBookConditionIsMissing() {
        addBookRequest.setCondition(null);
        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addBookRequest, authorRepository, userRepository));
        assertTrue(exception.getMessage().equals("Book condition must be specified when adding book"));
    }

    @Test
    void shouldThrowExceptionWhenBookConditionIsInvalid() {
        final String INVALID_CONDITION = "HORRIBLE";
        addBookRequest.setCondition(INVALID_CONDITION);
        RequestValidationException exception =
                assertThrows(RequestValidationException.class,
                        () -> execute(addBookRequest, authorRepository, userRepository));
        assertTrue(exception.getMessage().equals("Specified book condition is not a valid option : " + INVALID_CONDITION));
    }

    private void createAddBookRequest() {
        addBookRequest = new AddBookRequest();
        addBookRequest.setAuthorFirstname("Frank");
        addBookRequest.setAuthorLastname("Herbert");
        addBookRequest.setTitle("Dune");
        addBookRequest.setCondition("MINT");
        addBookRequest.setPublishedYear(1965);
        addBookRequest.setUsername("testuser");
    }
}