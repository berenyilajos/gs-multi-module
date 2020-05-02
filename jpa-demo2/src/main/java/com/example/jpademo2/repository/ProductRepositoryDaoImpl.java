package com.example.jpademo2.repository;

import com.example.jpademo2.entity.Product;
import com.example.jpademo2.qualyfier.Demo2Db;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class ProductRepositoryDaoImpl implements ProductRepositoryDao {

    @Autowired
    @Demo2Db
    private EntityManager em;

    @Override
    public List<Product> getCheaperOrEquals(BigDecimal price) {
        String queryText = "SELECT p FROM Product p WHERE p.price <= :price";
        TypedQuery<Product> query = em.createQuery(queryText, Product.class);
        query.setParameter("price", price);

        return query.getResultList();
    }
}
