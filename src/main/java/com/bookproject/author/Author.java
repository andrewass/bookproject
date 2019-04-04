package com.bookproject.author;

import com.bookproject.book.Book;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "T_AUTHOR")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class Author implements Serializable {

    private static final long serialVersionUID = -5715591885201704859L;

    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private Long id;

    @OneToMany(mappedBy = "author")
    //@JsonManagedReference
    private List<Book> publishedBooks;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author() {
    }
}
