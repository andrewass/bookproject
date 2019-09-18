package com.bookproject.book;

import com.bookproject.author.Author;
import com.bookproject.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_BOOK")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book implements Serializable {

    private static final long serialVersionUID = -839609830060833611L;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_CONDITION", nullable = false)
    BookCondition condition;

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User owner;

    @Column(name = "YEAR_PUBLISHED")
    private Integer yearPublished;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "GOODREADS_ID")
    private Long goodreadsID;

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "DATE_UPDATED")
    private LocalDateTime dateUpdated;

}
