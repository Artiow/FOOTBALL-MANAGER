package ru.vldf.sportsportal.dao.impl.common;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.definite.common.UserRoleDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.domain.common.UserRoleEntity;

import java.util.List;

@Repository
public class UserRoleDAOImpl extends AbstractDAOImpl<UserRoleEntity, Integer> implements UserRoleDAO {
    public UserRoleDAOImpl() {
        super(UserRoleEntity.class);
    }

    @Override
    public Integer save(UserRoleEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public UserRoleEntity findByID(Integer id) {
        return super.get(id);
    }

    public UserRoleEntity findByCode(String code) {
        List roles = getSession()
                .createQuery("from UserRoleEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((roles != null) && (roles.size() == 1)) return (UserRoleEntity) roles.get(0);
        else return null;
    }
}