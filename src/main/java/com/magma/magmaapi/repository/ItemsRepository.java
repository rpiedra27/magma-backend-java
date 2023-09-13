package com.magma.magmaapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.magma.magmaapi.model.Items;

public interface ItemsRepository extends MongoRepository<Items, String> {
  public Items findByName(String name);

  public List<Items> deleteByName(String name);

  public long count();
}