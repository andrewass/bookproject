package com.bookproject.book;

import java.util.List;
import java.util.Map;

import static com.bookproject.misc.GoodReadsAPI.getFieldsForBook;

public class FindRecentAddedBooksCommand {

    private FindRecentAddedBooksCommand(){}

    public static List<Book> execute(Integer count, BookRepository repository, String apiKey) {
        List<Book> books = repository.findRecentlyAddedBooks(count);
        books.forEach(book -> {
            if (book.getImageUrl() == null) {
                Map<String, String> fieldValues = getFieldsForBook(book.getTitle(), apiKey);
                book.setGoodreadsID(Long.parseLong(fieldValues.get("goodreads_id")));
                book.setImageUrl(fieldValues.get("image_url"));
                repository.save(book);
            }
        });
        return books;
    }
}
