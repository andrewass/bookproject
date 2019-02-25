package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "T_BOOK")
public class Book {

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_CONDITION", nullable = false)
    BookCondition condition;
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
    @JsonBackReference
    private Author author;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User owner;
    @Column(name = "YEAR_PUBLISHED")
    private Integer yearPublished;
    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @UpdateTimestamp
    @Column(name = "DATE_CHANGED")
    private Date dateChanged;

    public Book() {
    }
}
