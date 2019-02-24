package com.bookproject.book;

import lombok.Data;

@Data
class AddBookRequest {

    private String username;

    private String title;

    private String author_firstname;

    private String author_lastname;

    private Integer publishedYear;

    private String condition;
}
