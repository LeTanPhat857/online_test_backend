package com.sunflower.onlinetest.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;

public class GenericDAO<T extends iEntity> {

    private Class<T> persistenceClass;

    @PersistenceContext(unitName = "online_test_persistence")
    private EntityManager entityManager;

    private Class<T> getPersistenceClass() {
        if (this.persistenceClass == null) {
            this.persistenceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
        }
        return this.persistenceClass;
    }

    public T findById(Integer id) {
        return this.entityManager.find(getPersistenceClass(), id);
    }

    public T save(T entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return this.entityManager.merge(entity);
    }

    public T remove(Integer id) {
        T entity = findById(id);
        if (entity != null) {
            this.entityManager.remove(entity);
            return entity;
        }
        return null;
    }

    public T removeEntity(T entity) {
        if (!entityManager.contains(entity)) {
            update(entity);
        }
        entityManager.remove(entity);
        return entity;
    }

    public TypedQuery<T> createTypeQuery(String query) {
        return this.entityManager.createQuery(query, getPersistenceClass());
    }
}
