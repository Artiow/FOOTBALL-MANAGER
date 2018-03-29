package ru.vldf.sportsportal.dao.impl;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.RoleDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.model.RoleEntity;

import java.util.List;

@Repository
public class RoleDAOImpl extends AbstractDAOImpl<RoleEntity, Integer> implements RoleDAO {
    public RoleDAOImpl() {
        super(RoleEntity.class);
    }

    public RoleEntity findByCode(String code) {
        List users = getSession()
                .createQuery("from RoleEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((users != null) && (users.size() > 0)) return (RoleEntity) users.get(0);
        else return null;
    }
}
