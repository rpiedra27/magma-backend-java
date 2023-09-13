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

import com.magma.magmaapi.model.Orders;
import com.magma.magmaapi.repository.OrdersRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {
  private final OrdersRepository repository;

  public OrdersController(OrdersRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<Orders> findAll() {
    return repository.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Orders order) {
    repository.save(order);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{user}")
  public void update(@RequestBody Orders order, @PathVariable String user) {
    if (repository.findByUsername(user) != null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
    }
    repository.save(order);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{user}")
  public void delete(@PathVariable String user) {
    repository.deleteByUsername(user);
  }
}
