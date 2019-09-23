package com.bookproject.bookreview;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BookReviewResourceAssembler extends ResourceAssemblerSupport<BookReview, BookReviewResource> {

    public BookReviewResourceAssembler(Class<BookReviewController> bookReviewController,
                                       Class<BookReviewResource> bookReviewResource) {
        super(bookReviewController, bookReviewResource);
    }

    @Override
    public BookReviewResource toResource(BookReview bookReview) {
        return createResourceWithId(bookReview.getId(), bookReview);
    }
}
