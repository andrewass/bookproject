package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.author.AuthorRepository;
import com.bookproject.exception.RequestValidationException;
import com.bookproject.user.User;
import com.bookproject.user.UserRepository;

import java.util.Arrays;

public class BookUtils {

    private BookUtils() { }

    public static Book execute(AddBookRequest request, AuthorRepository authorRepository, UserRepository userRepository)
            throws RequestValidationException {
        validate(request);
        Author author = findAuthor(request.getAuthorFirstname(), request.getAuthorLastname(), authorRepository);
        User user = findUser(request.getUsername(), userRepository);
        return new BookBuilder()
                .withTitle(request.getTitle())
                .withCondition(BookCondition.valueOf(request.getCondition().toUpperCase()))
                .withOwner(user)
                .withAuthor(author)
                .withPublishedYear(request.getPublishedYear())
                .build();
    }

    private static User findUser(String username, UserRepository repository) {
        if (username == null) {
            return null;
        }
        return repository.findByUsername(username);
    }

    private static Author findAuthor(String firstname, String lastname, AuthorRepository repository) {
        if (firstname == null || lastname == null) {
            return null;
        }
        Author author = repository.findAuthorByName(firstname, lastname);
        return author == null ? repository.save(new Author(firstname, lastname)) : author;
    }

    private static void validate(AddBookRequest request) throws RequestValidationException {
        if (request.getTitle() == null) {
            throw new RequestValidationException("Title must be specified when adding book");
        }
        if (request.getCondition() == null) {
            throw new RequestValidationException("Book condition must be specified when adding book");
        }
        if (Arrays.stream(BookCondition.values()).noneMatch(bc -> bc.name().equalsIgnoreCase(request.getCondition()))) {
            throw new RequestValidationException("Specified book condition is not a valid option : "+request.getCondition());
        }
    }
}
