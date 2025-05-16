package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    
    @Id
    Integer id;
    @Column(name = "curveId")
    Integer curveId;
    @Column(name = "asOfDate")
    Timestamp asOfDate;
    Double term;
    Double value;
    @Column(name = "creationDate")
    Timestamp creationDate;
}
