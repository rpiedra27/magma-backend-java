package com.magma.ecommercesite.model;

import org.springframework.security.core.GrantedAuthority;

public record UserRole(
    Role role
) implements GrantedAuthority {
  @Override
  public String getAuthority() {
    return role.name();
  }
}
