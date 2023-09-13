package com.magma.magmaapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.magma.magmaapi.model.Ingredients;

public interface IngredientsRepository extends MongoRepository<Ingredients, String> {
  public Ingredients findByValue(String name);

  public List<Ingredients> deleteByValue(String name);

  public long count();
}
