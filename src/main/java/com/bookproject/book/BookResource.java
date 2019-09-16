package com.bookproject.book;

import com.bookproject.author.Author;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class BookResource extends ResourceSupport {

    private String title;
    private Author author;

    public BookResource(Book book){
        title = book.getTitle();
        author = book.getAuthor();
    }
}
