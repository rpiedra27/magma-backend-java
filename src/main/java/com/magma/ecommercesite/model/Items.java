package com.magma.ecommercesite.model;

import jakarta.validation.constraints.NotEmpty;

public record Items(
    @NotEmpty String name,
    String description,
    Integer price,
    String image,
    @NotEmpty String type) {

}
