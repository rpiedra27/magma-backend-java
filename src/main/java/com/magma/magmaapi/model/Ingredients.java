package com.magma.magmaapi.model;

import jakarta.validation.constraints.NotEmpty;

public record Ingredients(
    @NotEmpty String label,
    @NotEmpty Integer price,
    @NotEmpty String value,
    @NotEmpty String type) {

}
