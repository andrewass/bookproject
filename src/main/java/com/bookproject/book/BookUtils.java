package com.bookproject.book;

import com.bookproject.exception.RequestValidationException;
import com.bookproject.user.User;
import com.bookproject.user.UserRepository;

import java.util.Arrays;

public class BookUtils {

    private BookUtils() { }

    public static Book execute(AddBookRequest request, UserRepository userRepository)
            throws RequestValidationException {
        validate(request);
              User user = findUser(request.getUsername(), userRepository);
        return new BookBuilder()
                .withTitle(request.getTitle())
                .withCondition(BookCondition.valueOf(request.getCondition().toUpperCase()))
                .withOwner(user)
                .withPublishedYear(request.getPublishedYear())
                .build();
    }

    private static User findUser(String username, UserRepository repository) {
        if (username == null) {
            return null;
        }
        return repository.findByUsername(username);
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
