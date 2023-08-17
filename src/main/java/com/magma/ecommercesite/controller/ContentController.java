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

import com.magma.ecommercesite.model.Content;
import com.magma.ecommercesite.repository.ContentCollectionRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {
  private final ContentCollectionRepository repository;

  public ContentController(ContentCollectionRepository repository){
    this.repository = repository;
  }

  @GetMapping("")
  public List<User> findAll(){
    return repository.findAllUsers();

  }

  @GetMapping("/{email}")
  public List<Content> findUserByEmail(@PathVariable String email){
    return repository.findUserByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void createUser(@RequestBody Content content) { // indicates that a method parameter should be bound to the body of
                              // a web request
    repository.save(content);
  }
}
