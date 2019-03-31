package com.bookproject.book.review;

import lombok.Data;

@Data
public class AddReviewRequest {

    String review;

    Long userId;

    String title;

    Integer stars;
}
