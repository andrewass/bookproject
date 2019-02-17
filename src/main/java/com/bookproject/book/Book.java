package com.bookproject.book;

import com.bookproject.author.Author;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "T_BOOK")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private com.bookproject.user.User owner;

    @Column(name = "YEAR_PUBLISHED")
    private Date datePublished;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_CONDITION", nullable = false)
    BookCondition condition;

    public Book(String title) {
        this.title = title;
    }
}
