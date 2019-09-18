package com.bookproject.bookreview;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BookReviewResourceAssembler extends ResourceAssemblerSupport<BookReview, BookResource> {

    public BookReviewResourceAssembler(Class<BookReviewController> bookReviewController,
                                       Class<BookResource> bookReviewResource) {
        super(bookReviewController, bookReviewResource);
    }

    @Override
    public BookResource toResource(BookReview bookReview) {
        return createResourceWithId(bookReview.getId(), bookReview);
    }
}
