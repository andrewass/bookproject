package com.bookproject.book;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
class AddBookRequest {

    private String username;

    private String title;

    private String authorFirstname;

    private String authorLastname;

    private Integer publishedYear;

    private String condition;
}
