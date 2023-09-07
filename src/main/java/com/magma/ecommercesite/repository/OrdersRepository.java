package com.magma.ecommercesite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.magma.ecommercesite.model.Orders;

public interface OrdersRepository extends MongoRepository<Orders, String> {
  public Orders findByUsername(String name);

  public List<Orders> deleteByUsername(String name);
}
