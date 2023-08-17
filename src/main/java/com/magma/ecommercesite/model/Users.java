package com.magma.ecommercesite.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public record Users(
    @Id
    Integer id,
    @NotEmpty
    String username,
    @NotEmpty
    String email,
    @NotEmpty
    String password) {
}
