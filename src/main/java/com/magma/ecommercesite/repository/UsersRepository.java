package com.magma.ecommercesite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.magma.ecommercesite.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {

  List<Users> findAll();

  public long count();

  Users findUsersByUsername(String name);

  Users findUsersByEmail(String email);

  Boolean existsByEmail(String email);

  void deleteByEmail(String email);
}
