package com.example.jpademo.repository;

import com.example.jpademo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryDao {
    List<User> findByName(String name);
    User findOneById(long id);
}
