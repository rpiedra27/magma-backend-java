package com.magma.ecommercesite.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.magma.ecommercesite.model.Users;
import com.magma.ecommercesite.repository.UsersRepository;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {
  private final UsersRepository repository;

  public MongoAuthUserDetailService(UsersRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Users user = repository.findUsersByUsername(userName);
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    user.getAuthorities()
        .forEach(role -> {
          grantedAuthorities.add(new SimpleGrantedAuthority(role.role()
              .name()));
        });
    return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }
}
