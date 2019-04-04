package com.bookproject.bookreview;

import lombok.Data;

@Data
public class AddBookReviewRequest {

    String review;

    Long userId;

    String title;

    Integer stars;
}
