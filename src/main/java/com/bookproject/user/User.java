package com.bookproject.user;

import com.bookproject.bookreview.BookReview;
import com.bookproject.misc.Country;
import com.bookproject.userreview.UserReview;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @JsonIgnoreProperties("owner")
    private List<com.bookproject.book.Book> bookList;

    @Column(nullable = false)
    @Max(value = 10L)
    @Min(value = 0L)
    private Integer rating;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESIDING_COUNTRY")
    private Country country;

    public User(){}

}
