package com.magma.ecommercesite.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import com.magma.ecommercesite.model.Users;

public interface UsersRepository extends ListCrudRepository<Users, Integer> {

  List<Users> findAllByUsername(String username);

  List<Users> findAllByEmail(String email);

  Boolean existsByEmail(String email);

  void deleteByEmail(String email);
}
