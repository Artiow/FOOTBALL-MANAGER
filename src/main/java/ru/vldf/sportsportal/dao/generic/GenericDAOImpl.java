package ru.vldf.sportsportal.dao.generic;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {
    private Class<T> aClass;
    private SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(aClass);
    }

//    ==================================================================================
//    === BASIC REQUESTS

    public PK save(T entity) {
        return (PK) getSession().save(entity);
    }

    public T get(PK id) {
        return getSession().get(aClass, id);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public List<T> list() {
        return (List<T>) createCriteria().list();
    }
}
