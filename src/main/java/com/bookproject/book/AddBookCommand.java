package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.author.AuthorRepository;
import com.bookproject.exception.RequestValidationException;

import java.util.Arrays;

public class AddBookCommand {

    private AddBookCommand() {
    }

    public static Book execute(AddBookRequest request, BookRepository repository, AuthorRepository authorRepository)
            throws RequestValidationException {
        validate(request);
        Author author = findAuthor();
        Book book = new Book(request.getTitle());
        //repository.save(book);
        return book;
    }

    private static Author findAuthor() {
        return null;
    }

    private static Author createAuthor() {
        return null;
    }

    private static void validate(AddBookRequest request) throws RequestValidationException {
        if (request.getUsername() == null) {
            throw new RequestValidationException("Username must be specified");
        }
        if (request.getAuthor_firstName() == null) {
            throw new RequestValidationException("Authors first name must be specified");
        }
        if (request.getAuthor_lastName() == null) {
            throw new RequestValidationException("Authors last name must be specified");
        }
        if (request.getCondition() == null) {
            throw new RequestValidationException("Book condition must be specified");
        }
        if (Arrays.stream(BookCondition.values()).noneMatch(bc -> bc.name().equalsIgnoreCase(request.getCondition()))) {
            throw new RequestValidationException("Specified book condition is not a valid option");
        }
    }
}
