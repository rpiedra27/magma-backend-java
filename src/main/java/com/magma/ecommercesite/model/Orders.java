package com.magma.ecommercesite.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public record Orders(
    @Id Integer id,
    @NotEmpty Integer user_id,
    @NotEmpty Integer cost,
    @NotEmpty Timestamp timestamp) {

}
