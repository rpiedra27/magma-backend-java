package com.magma.ecommercesite.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public record Ingredients(
    @Id Integer id,
    @NotEmpty String label,
    @NotEmpty Integer price,
    @NotEmpty String value) {

}
