package com.magma.ecommercesite.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.magma.ecommercesite.model.Ingredients;

public interface IngredientsRepository extends ListCrudRepository<Ingredients, Integer>{
  
}
