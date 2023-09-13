package com.magma.magmaapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magma.magmaapi.model.UserDetailsImpl;
import com.magma.magmaapi.model.Users;
import com.magma.magmaapi.repository.UsersRepository;

@Service
public class MongoUserDetailsService implements UserDetailsService {
  private final UsersRepository repository;

  public MongoUserDetailsService(UsersRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Users user = repository.findUsersByEmail(email);
    return UserDetailsImpl.build(user);
  }
}
