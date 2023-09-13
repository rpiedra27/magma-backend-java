package com.magma.magmaapi.model;

import java.util.Set;
import jakarta.validation.constraints.NotEmpty;

public record Users(
    @NotEmpty String username,
    @NotEmpty String email,
    @NotEmpty String password,
    @NotEmpty Set<Role> roles) {
}
