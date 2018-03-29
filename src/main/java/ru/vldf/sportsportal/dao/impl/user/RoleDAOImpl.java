package ru.vldf.sportsportal.dao.impl.user;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.definite.user.RoleDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.model.user.RoleEntity;

import java.util.List;

@Repository
public class RoleDAOImpl extends AbstractDAOImpl<RoleEntity, Integer> implements RoleDAO {
    public RoleDAOImpl() {
        super(RoleEntity.class);
    }

    public RoleEntity findByCode(String code) {
        List roles = getSession()
                .createQuery("from RoleEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((roles != null) && (roles.size() > 0)) return (RoleEntity) roles.get(0);
        else return null;
    }
}