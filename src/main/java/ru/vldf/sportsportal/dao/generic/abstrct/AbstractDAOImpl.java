package ru.vldf.sportsportal.dao.generic.abstrct;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class AbstractDAOImpl<E, PK extends Serializable> implements AbstractDAO<E, PK> {
    private Class<E> aClass;
    private SessionFactory sessionFactory;

    public AbstractDAOImpl(Class<E> aClass) {
        this.aClass = aClass;
    }

    @Autowired
    private void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        try { return sessionFactory.getCurrentSession(); }
        catch (HibernateException e) { return sessionFactory.openSession(); }
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(aClass);
    }

//    ==================================================================================
//    === BASIC REQUESTS

    public E get(PK id) {
        return getSession().get(aClass, id);
    }

    public PK save(E entity) {
        return (PK) getSession().save(entity);
    }

    public void update(E entity) {
        getSession().update(entity);
    }

    public void delete(E entity) {
        getSession().delete(entity);
    }

    public List<E> list() {
        return (List<E>) createCriteria().list();
    }
}