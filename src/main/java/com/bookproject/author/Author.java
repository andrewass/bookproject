package com.bookproject.author;

import com.bookproject.book.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "T_AUTHOR")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Book> publishedBooks;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {
    }
}
