package com.bookproject.book;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BookResourceAssembler extends ResourceAssemblerSupport<Book, BookResource> {

    public BookResourceAssembler() {
        super(BookController.class, BookResource.class);
    }

    @Override
    protected BookResource instantiateResource(Book book) {
        return new BookResource(book);
    }

    @Override
    public BookResource toResource(Book book) {
        return createResourceWithId(book.getId(), book);
    }
}
