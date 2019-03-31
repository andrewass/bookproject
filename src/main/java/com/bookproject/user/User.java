package com.bookproject.user;

import com.bookproject.book.Book;
import com.bookproject.book.review.BookReview;
import com.bookproject.misc.Country;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "T_USER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class User implements Serializable {

    private static final long serialVersionUID = 4929254147052461693L;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "owner")
    //@JsonManagedReference
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    //@JsonManagedReference
    private List<BookReview> bookReviewList;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Double bookCoins;

    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "COUNTRY")
    private Country country;

    public User() {
    }

    public User(String username, String firstName, String lastName,
                String password, Country country, String emailAddress) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.country = country;
        this.emailAddress = emailAddress;
        bookCoins = 0.00;
        rating = 6;
    }

    public void addReview(BookReview bookReview) {
        bookReviewList.add(bookReview);
    }
}
