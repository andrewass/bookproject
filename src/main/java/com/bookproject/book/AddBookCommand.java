package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.author.AuthorRepository;
import com.bookproject.exception.RequestValidationException;
import com.bookproject.user.User;
import com.bookproject.user.UserRepository;

import java.util.Arrays;

public class AddBookCommand {

    private AddBookCommand() {
    }

    public static Book execute(AddBookRequest request, BookRepository bookRepository,
                               AuthorRepository authorRepository, UserRepository userRepository)
            throws RequestValidationException {
        validate(request);
        Author author = findAuthor(request.getAuthor_firstname(), request.getAuthor_lastname(), authorRepository);
        User user = findUser(request.getUsername(), userRepository);
        return new BookBuilder()
                .withTitle(request.getTitle())
                .withCondition(BookCondition.valueOf(request.getCondition().toUpperCase()))
                .withAuthor(author)
                .build();
    }

    private static User findUser(String username, UserRepository repository) {
        return null;
    }

    private static Author findAuthor(String firstname, String lastname, AuthorRepository repository) {
        return null;
    }

    private static Author createAuthor() {
        return null;
    }

    private static void validate(AddBookRequest request) throws RequestValidationException {
        if (request.getTitle() == null) {
            throw new RequestValidationException("Title must be specified");
        }
        if (request.getCondition() == null) {
            throw new RequestValidationException("Book condition must be specified");
        }
        if (Arrays.stream(BookCondition.values()).noneMatch(bc -> bc.name().equalsIgnoreCase(request.getCondition()))) {
            throw new RequestValidationException("Specified book condition is not a valid option");
        }
    }
}
