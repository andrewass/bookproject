package com.bookproject.book;

import lombok.Data;

@Data
public class AddBookRequest {

    String username;

    String title;

    String author_firstName;

    String author_lastName;

    Integer publishedYear;

    String condition;
}
