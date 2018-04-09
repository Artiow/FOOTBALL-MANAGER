package ru.vldf.sportsportal.dao.generic.abstrct;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<E, PK extends Serializable> {
    E get(PK id);

    PK save(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> list();
}