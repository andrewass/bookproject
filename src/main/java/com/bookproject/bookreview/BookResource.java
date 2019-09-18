package com.bookproject.bookreview;

import com.bookproject.user.UserResource;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
class BookResource extends ResourceSupport {

    private UserResource user;
    private String review;

    BookResource(BookReview bookReview){

    }

}
