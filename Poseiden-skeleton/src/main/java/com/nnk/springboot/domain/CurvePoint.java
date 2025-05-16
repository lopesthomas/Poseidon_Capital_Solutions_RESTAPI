package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "curvepoint")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "curveId")
    @NotNull(message = "Curve Id most be not null")
    @Positive(message = "Curve Id be positive")
    Integer curveId;
    @Column(name = "asOfDate")
    Timestamp asOfDate;
    @Column(name = "term")
    @NotNull(message = "Term is mandatory")
    @Positive(message = "Term must be positive")
    Double term;
    @Column(name = "value")
    @NotNull(message = "Value is mandatory")
    @Positive(message = "Value must be positive")
    Double value;
    @Column(name = "creationDate")
    Timestamp creationDate;
}
