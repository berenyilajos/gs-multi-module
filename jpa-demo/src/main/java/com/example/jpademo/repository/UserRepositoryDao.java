package com.example.jpademo.repository;

import com.example.jpademo.entity.User;

import java.util.List;

public interface UserRepositoryDao {

    List<User> getByUsername(String username);

}
