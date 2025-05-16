package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    Integer id;
    @Column(name = "moodysRating")
    String moodysRating;
    @Column(name = "sandPRating")
    String sandPRating;
    @Column(name = "fitchRating")
    String fitchRating;
    @Column(name = "orderNumber")
    Integer orderNumber;
}
