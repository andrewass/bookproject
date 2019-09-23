package com.bookproject.bookreview;

import com.bookproject.user.UserResource;
import com.bookproject.user.UserResourceAssembler;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;

@Getter
class BookReviewResource extends ResourceSupport {

    @Autowired
    UserResourceAssembler userResourceAssembler;

    private UserResource user;
    private String review;

    BookReviewResource(BookReview bookReview){
    }

}
