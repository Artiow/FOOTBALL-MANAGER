package ru.vldf.sportsportal.dao.generic.abstrct;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<T, PK extends Serializable> {
    T get(PK id);

    PK save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> list();
}