package com.bookproject.bookreview;

import com.bookproject.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "T_BOOK_REVIEW")
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
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "DATE_CHANGED")
    private LocalDateTime dateChanged;

    BookReview(String review, String title, Integer stars){
        this.review = review;
        this.title = title;
        this.stars = stars;
    }

    BookReview(){}
}
