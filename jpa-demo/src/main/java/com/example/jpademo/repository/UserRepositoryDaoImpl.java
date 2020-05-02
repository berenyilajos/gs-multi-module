package com.example.jpademo.repository;

import com.example.jpademo.entity.User;
import com.example.jpademo.qualyfier.DemoDb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepositoryDaoImpl implements UserRepositoryDao {

    @Autowired
    @DemoDb
    private EntityManager em;

    @Override
    public List<User> getByUsername(String username) {
        String queryText = "SELECT u FROM User u WHERE u.name = :username";
        TypedQuery<User> query = em.createQuery(queryText, User.class);
        query.setParameter("username", username);

        return query.getResultList();
    }
}
