package com.bookproject.bookreview;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class BookReviewResource extends ResourceSupport {

    private String username;
    private String review;

    BookReviewResource(BookReview bookReview){

    }

}
