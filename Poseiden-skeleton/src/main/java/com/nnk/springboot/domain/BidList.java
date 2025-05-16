package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "BidListId", nullable = false)
    Integer BidListId;
    @Column(name = "account", length = 30, nullable = false)
    String account;
    @Column(name = "type", length = 30, nullable = false)
    String type;
    @Column(name = "bidQuantity")
    Double bidQuantity;
    @Column(name = "askQuantity")
    Double askQuantity;
    Double bid;
    Double ask;
    @Column(name = "benchmark", length = 125)
    String benchmark;
    @Column(name = "bidListDate")
    Timestamp bidListDate;
    @Column(name = "commentary", length = 125)
    String commentary;
    @Column(name = "security", length = 125)
    String security;
    @Column(name = "status", length = 10)
    String status;
    @Column(name = "trader", length = 125)
    String trader;
    @Column(name = "book", length = 125)
    String book;
    @Column(name = "creationName", length = 125)
    String creationName;
    @Column(name = "creationDate")
    Timestamp creationDate;
    @Column(name = "revisionName", length = 125)
    String revisionName;
    @Column(name = "revisionDate")
    Timestamp revisionDate;
    @Column(name = "dealName", length = 125)
    String dealName;
    @Column(name = "dealType", length = 125)
    String dealType;
    @Column(name = "sourceListId", length = 125)
    String sourceListId;
    @Column(name = "side", length = 125)
    String side;
}
