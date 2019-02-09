package com.bookproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "T_AUTHOR")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @OneToMany(mappedBy = "author")
    private List<Book> publishedBooks;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public Author(){}

    public Author(String authorId){
        this.authorId = Long.parseLong(authorId);
    }
}
