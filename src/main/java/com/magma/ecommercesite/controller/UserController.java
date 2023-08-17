package com.magma.ecommercesite.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.magma.ecommercesite.model.User;
import com.magma.ecommercesite.repository.UserCollectionRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserCollectionRepository repository;

  public UserController(UserCollectionRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<User> findAllUsers() {
    return repository.findAllUsers();
  }

  @GetMapping("/{email}")
  public User findUserByEmail(@PathVariable String email) {
    return repository.findUserByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void createUser(@RequestBody User user) {
    repository.save(user);
  }
}
