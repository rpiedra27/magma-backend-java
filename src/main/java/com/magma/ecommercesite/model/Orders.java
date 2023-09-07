package com.magma.ecommercesite.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record Orders(
    @NotEmpty Integer username,
    @NotEmpty Integer cost,
    @NotEmpty Timestamp timestamp,
    @NotEmpty List<Items> items) {
}
