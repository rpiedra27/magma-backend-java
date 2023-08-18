package com.magma.ecommercesite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magma.ecommercesite.model.Items;
import com.magma.ecommercesite.repository.ItemsRepository;

@RestController
@RequestMapping("/api/items")
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
}
