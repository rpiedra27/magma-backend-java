package com.magma.ecommercesite.model;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotEmpty;

public record Users(
    @NotEmpty String username,
    @NotEmpty String email,
    @NotEmpty String password,
    @NotEmpty Set<UserRole> userRoles) implements UserDetails {

  @Override
  public Set<UserRole> getAuthorities() {
    return this.userRoles;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
