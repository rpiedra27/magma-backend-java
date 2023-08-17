package com.magma.ecommercesite.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.magma.ecommercesite.model.Users;

public interface UsersRepository extends ListCrudRepository<Users, Integer>{

  Users findByEmailIs(String email);

  Boolean existsByEmailIs(String email);

  void deleteByEmailIs(String email);
}
