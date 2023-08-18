package com.magma.ecommercesite.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public record Items(
    @Id Integer id,
    @NotEmpty String name,
    @NotEmpty String description,
    @NotEmpty Integer price,
    @NotEmpty String image_url) {

}
