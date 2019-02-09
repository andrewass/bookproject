package com.bookproject.entity;

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


}
