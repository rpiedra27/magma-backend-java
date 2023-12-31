package com.magma.magmaapi.controller;

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

import com.magma.magmaapi.model.Items;
import com.magma.magmaapi.repository.ItemsRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemsController {
  private final ItemsRepository repository;

  public ItemsController(ItemsRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<Items> findAll() {
    return repository.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Items item) {
    repository.save(item);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{name}")
  public void update(@RequestBody Items item, @PathVariable String name) {
    if (repository.findByName(name) != null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
    }
    repository.save(item);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{name}")
  public void delete(@PathVariable String name) {
    repository.deleteByName(name);
  }
}
