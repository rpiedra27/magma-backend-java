package com.magma.ecommercesite.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.magma.ecommercesite.model.Items;

public interface ItemsRepository extends ListCrudRepository<Items, Integer> {

}
