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
@Table(name = "trade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields

    @Id
    @Column(name = "tradeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message = "Account is mandatory")
    String account;
    @NotBlank(message = "Type is mandatory")
    String type;
    @Column(name = "buyQuantity")
    @Positive(message = "Buy Quantity must be positive or zero")
    @NotNull(message = "Buy Quantity is mandatory")
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
