package com.bookproject.user;

import com.bookproject.book.Book;
import com.bookproject.misc.Country;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "T_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long userId;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "owner")
    private List<Book> bookList;

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
    @Column(name = "RESIDING_COUNTRY")
    private Country residingCountry;

    public User(){}

    public User(String username, String firstName, String lastName, String password, Country residingCountry){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.residingCountry = residingCountry;
    }

}
