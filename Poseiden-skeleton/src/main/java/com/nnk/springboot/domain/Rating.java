package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "moodysRating")
    @NotBlank(message = "Moodys Rating is mandatory")
    String moodysRating;
    @Column(name = "sandPRating")
    @NotBlank(message = "SandP Rating is mandatory")
    String sandPRating;
    @Column(name = "fitchRating")
    @NotBlank(message = "Fitch Rating is mandatory")
    String fitchRating;
    @Column(name = "orderNumber")
    @NotNull(message = "Order Number is mandatory")
    Integer order;
}
