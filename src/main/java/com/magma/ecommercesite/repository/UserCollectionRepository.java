package com.magma.ecommercesite.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.magma.ecommercesite.model.Users;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UserCollectionRepository {
  private final List<Users> userList = new ArrayList<>();

  public List<Users> findAllUsers() {
    return userList;
  }

  public Optional<Users> findUserByEmail(String email) {
    return userList.stream().filter(u -> u.email().equals(email)).findFirst();
  }

  public void save(Users user) {
    userList.add(user);
  }

  @PostConstruct
  private void init() {
    Users user = new Users(1, "Rodrigo", "rpiedra_27@outlook.com", "password");
    userList.add(user);
  }

  public boolean existsByEmail(String email) {
    return userList.stream().filter(u -> u.email().equals(email)).count() == 1;
  }

  public void delete(String email){
    userList.removeIf(u -> u.email().equals(email));
  }
}
