package com.example.jpademo2.repository;

import com.example.jpademo2.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>, ProductRepositoryDao {
    List<Product> findByName(String name);
    Product findOneById(long id);
}
