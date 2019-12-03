package com.example.jpademo.dao.impl;

import com.example.jpademo.dao.base.BaseEntityDao;
import com.example.jpademo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserEntityDao extends BaseEntityDao<User, Long> {

    @Autowired
    public UserEntityDao(@Qualifier("userEntityManagerFactory") EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
