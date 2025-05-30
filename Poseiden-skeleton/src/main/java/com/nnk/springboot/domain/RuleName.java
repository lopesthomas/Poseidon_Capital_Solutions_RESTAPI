package com.nnk.springboot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rulename")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank(message = "Name is mandatory")
    String name;
    String description;
    String json;
    String template;
    @Column(name = "sqlStr")
    String sql;
    @Column(name = "sqlPart")
    String sqlPart;
}
