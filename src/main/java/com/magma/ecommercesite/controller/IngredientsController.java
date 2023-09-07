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

import com.magma.ecommercesite.model.Ingredients;
import com.magma.ecommercesite.repository.IngredientsRepository;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin
public class IngredientsController {
  private final IngredientsRepository repository;

  public IngredientsController(IngredientsRepository repository) {
    this.repository = repository;
  }
  
  @RolesAllowed("ROLE_USER")
  @GetMapping("")
  public List<Ingredients> findAll() {
    return repository.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Ingredients ingredient) {
    repository.save(ingredient);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{name}")
  public void update(@RequestBody Ingredients ingredient, @PathVariable String name) {
    if (repository.findByValue(name) != null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found");
    }
    repository.save(ingredient);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{name}")
  public void delete(@PathVariable String name) {
    repository.deleteByValue(name);
  }
}
