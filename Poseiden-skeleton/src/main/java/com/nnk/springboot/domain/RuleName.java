package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

    @Id
    Integer id;
    String name;
    String description;
    String json;
    String template;
    @Column(name = "sqlStr")
    String sqlStr;
    @Column(name = "sqlPart")
    String sqlPart;
}
