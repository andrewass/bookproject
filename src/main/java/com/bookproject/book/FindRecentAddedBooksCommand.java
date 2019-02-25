package com.bookproject.book;

import java.util.List;

public class FindRecentAddedBooksCommand {

    public static List<Book> execute(Integer count, BookRepository repository) {
        return repository.findRecentlyAddedBooks(count);
    }
}
