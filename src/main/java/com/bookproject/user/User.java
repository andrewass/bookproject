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

    private String username;

    @OneToMany(mappedBy = "owner")
    private List<Book> bookList;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private Double bookCoins;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESIDING_COUNTRY")
    private Country residingCountry;

}
