package com.bookproject.entity;

import com.bookproject.utility.BookCondition;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "T_BOOK")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID", nullable = false)
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
    private User owner;

    @Column(name = "DATE_PUBLISHED")
    private Date datePublished;

    @Column(name = "BOOK_CONDITION", nullable = false)
    BookCondition condition;

    public Book() {
    }
}
