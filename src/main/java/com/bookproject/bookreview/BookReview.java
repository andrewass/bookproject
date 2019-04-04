package com.bookproject.bookreview;

import com.bookproject.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity(name = "T_BOOK_REVIEW")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class BookReview implements Serializable {

    private static final long serialVersionUID = -3786479255636334128L;

    @Id
    @GeneratedValue
    @Column(name = "REVIEW_ID")
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String review;

    private Integer stars;

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "DATE_CHANGED")
    private Date dateChanged;

    public BookReview(String review, String title, Integer stars){
        this.review = review;
        this.title = title;
        this.stars = stars;
    }

    public BookReview(){}
}
