package com.bookproject.userreview;

import com.bookproject.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_USER_REVIEW")
public class UserReview {

    @Id
    @GeneratedValue
    private Long id;

    private User reviewed;

    private User reviewer;
}
