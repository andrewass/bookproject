package com.bookproject.user;

import com.bookproject.bookreview.BookReview;
import com.bookproject.misc.Country;
import com.bookproject.userreview.UserReview;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "T_USER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "owner")
    private List<com.bookproject.book.Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<BookReview> bookReviewList;

    @OneToMany(mappedBy = "reviewed")
    private List<UserReview> reviewedList;

    @OneToMany(mappedBy = "reviewer")
    private List<UserReview> reviewerList;

    @Column(nullable = false)
    @Max(value = 10L)
    @Min(value = 0L)
    private Integer rating;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "COUNTRY")
    private Country country;

    public User(){}

    public User(String username, String firstName, String lastName,
                String password, Country country, String emailAddress) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.country = country;
        this.emailAddress = emailAddress;
        rating = 6;
    }

    public void addReview(BookReview bookReview) {
        if(bookReviewList == null){
            bookReviewList = new ArrayList<>();
        }
        bookReviewList.add(bookReview);
    }
}
