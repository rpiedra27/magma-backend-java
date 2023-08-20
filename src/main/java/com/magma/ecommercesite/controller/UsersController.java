package com.magma.ecommercesite.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.magma.ecommercesite.model.Users;
import com.magma.ecommercesite.repository.UsersRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
  private final UsersRepository repository;

  public UsersController(UsersRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<Users> findAll() {
    return repository.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Users user) {
    repository.save(user);
  }

  @GetMapping("/filter/user/{username}")
  public List<Users> findAllByUsername(@PathVariable String username) {
    return repository.findAllByUsername(username);
  }

  @GetMapping("/filter/email/{email}")
  public List<Users> findAllByEmail(@PathVariable String email) {
    return repository.findAllByEmail(email);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{email}")
  public void update(@RequestBody Users user, @PathVariable String email) {
    if (!repository.existsByEmail(email)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    repository.save(user);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{email}")
  public void deleteByEmail(@PathVariable String email) {
    repository.deleteByEmail(email);
  }

}
