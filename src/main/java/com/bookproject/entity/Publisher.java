package com.bookproject.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "T_PUBLISHER")
public class Publisher {

    @Id
    @GeneratedValue
    private Long publisherId;

    private String publisherName;


}
