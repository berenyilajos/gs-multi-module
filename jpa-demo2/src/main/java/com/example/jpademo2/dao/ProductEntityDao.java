package com.example.jpademo2.dao;

import com.example.common.dao.BaseEntityDao;
import com.example.jpademo2.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductEntityDao extends BaseEntityDao<Product, Long> {

    @Autowired
    public ProductEntityDao(@Qualifier("demo2EntityManagerFactory") EntityManager entityManager) {
        super(entityManager, Product.class);
    }

    public List<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        TypedQuery<Product> query = getEntityManager().createQuery(jpql, Product.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

    public List<Product> findProductsCheaperThen(BigDecimal price) {
        String jpql = "SELECT p FROM Product p WHERE p.price < :price";
        TypedQuery<Product> query = getEntityManager().createQuery(jpql, Product.class);
        query.setParameter("price", price);

        return query.getResultList();
    }
}
