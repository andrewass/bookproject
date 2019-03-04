package com.bookproject.book;

import java.util.List;
import java.util.Map;

import static com.bookproject.misc.GoodreadsAPI.getFieldsForBook;

public class FindRecentAddedBooksCommand {

    public static List<Book> execute(Integer count, BookRepository repository, String apiKey) {
        List<Book> books = repository.findRecentlyAddedBooks(count);
        books.forEach(book -> {
            if (book.getImageUrl() == null) {
                Map<String, String> fieldValues = getFieldsForBook(book.getTitle(), apiKey);

            }
        });
        return books;
    }
}
