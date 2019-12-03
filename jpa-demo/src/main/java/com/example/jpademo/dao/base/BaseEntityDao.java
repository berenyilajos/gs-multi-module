package com.example.jpademo.dao.base;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseEntityDao<T, ID> {

    private EntityManager entityManager;
    private Class<T> entityClass;

    public BaseEntityDao(
            EntityManager entityManager,
            Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public T find(ID id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        String jpql = "select e from " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    public void update(T entity, Consumer<T> update) throws Exception {
        update.accept(entity);
    }

    public T save(T entity) {
        return entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }

    public void removeById(ID id) {
        T entity = find(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
