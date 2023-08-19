package com.magma.ecommercesite.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.magma.ecommercesite.model.Orders;

public interface OrdersRepository extends ListCrudRepository<Orders, Integer> {

}
