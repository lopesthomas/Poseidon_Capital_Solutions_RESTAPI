package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields

    @Id
    @Column(name = "tradeId")
    Integer tradeId;
    String account;
    String type;
    @Column(name = "buyQuantity")
    Double buyQuantity;
    @Column(name = "sellQuantity")
    Double sellQuantity;
    @Column(name = "buyPrice")
    Double buyPrice;
    @Column(name = "sellPrice")
    Double sellPrice;
    String benchmark;
    @Column(name = "tradeDate")
    Timestamp tradeDate;
    String security;
    String status;
    String trader;
    String book;
    @Column(name = "creationName")
    String creationName;
    @Column(name = "creationDate")
    Timestamp creationDate;
    @Column(name = "revisionName")
    String revisionName;
    @Column(name = "revisionDate")
    Timestamp revisionDate;
    @Column(name = "dealName")
    String dealName;
    @Column(name = "dealType")
    String dealType;
    @Column(name = "sourceListId")
    String sourceListId;
    String side;

}
