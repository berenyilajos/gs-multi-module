package com.example.jpademo2.repository;

import com.example.jpademo2.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepositoryDao {

    List<Product> getCheaperOrEquals(BigDecimal price);

}
