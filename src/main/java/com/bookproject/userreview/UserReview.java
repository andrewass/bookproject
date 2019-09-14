package com.bookproject.userreview;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_USER_REVIEW")
public class UserReview {

    @Id
    @GeneratedValue
    private Long id;

}
