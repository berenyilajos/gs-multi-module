package com.example.jpademo.dao;

import com.example.common.dao.BaseEntityDao;
import com.example.jpademo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserEntityDao extends BaseEntityDao<User, Long> {

    @Autowired
    public UserEntityDao(@Qualifier("demoEntityManagerFactory") EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
