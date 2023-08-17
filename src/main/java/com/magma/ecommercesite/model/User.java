package com.magma.ecommercesite.model;

public record User(
    Integer id,
    String name,
    String email,
    String password) {
}
