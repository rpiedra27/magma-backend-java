package com.magma.ecommercesite.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.magma.ecommercesite.model.User;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UserCollectionRepository {
  private final List<User> userList = new ArrayList<>();

  public List<User> findAllUsers() {
    return userList;
  }

  public Optional<User> findUserByEmail(String email) {
    return userList.stream().filter(u -> u.email().equals(email)).findFirst();
  }

  public void save(User user) {
    userList.add(user);
  }

  @PostConstruct
  private void init() {
    User user = new User(1, "Rodrigo", "rpiedra_27@outlook.com", "password");
    userList.add(user);
  }
}
