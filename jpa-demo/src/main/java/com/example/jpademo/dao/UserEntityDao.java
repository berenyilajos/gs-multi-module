package com.example.jpademo.dao;

import com.example.common.dao.BaseEntityDao;
import com.example.jpademo.entity.User;
import com.example.jpademo.qualyfier.DemoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserEntityDao extends BaseEntityDao<User, Long> {

    @Autowired
    public UserEntityDao(@DemoDb EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
