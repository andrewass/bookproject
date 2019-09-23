package com.bookproject.book;

import com.bookproject.user.UserResource;
import com.bookproject.user.UserResourceAssembler;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class BookResource extends ResourceSupport {

    private String title;
    private UserResource user;

    BookResource(Book book) {
        UserResourceAssembler userResourceAssembler = new UserResourceAssembler();
        title = book.getTitle();
        user = userResourceAssembler.toResource(book.getOwner());
    }
}
