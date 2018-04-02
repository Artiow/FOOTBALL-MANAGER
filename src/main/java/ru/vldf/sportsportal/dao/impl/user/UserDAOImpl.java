package ru.vldf.sportsportal.dao.impl.user;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.definite.user.UserDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.model.user.RoleEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<UserEntity, Integer> implements UserDAO {
    public UserDAOImpl() {
        super(UserEntity.class);
    }

    public Integer saveUser(UserEntity user) {
        return this.save(user);
    }

//    ==================================================================================
//    === FIND

    public UserEntity findByID(Integer id) {
        List users = getSession()
                .createQuery("from UserEntity where id=?")
                .setParameter(0, id)
                .list();

        if ((users != null) && (users.size() == 1)) return (UserEntity) users.get(0);
        else return null;
    }

    public UserEntity findByLogin(String login) {
        List users = getSession()
                .createQuery("from UserEntity where login=?")
                .setParameter(0, login)
                .list();

        if ((users != null) && (users.size() == 1)) return (UserEntity) users.get(0);
        else return null;
    }

    public UserEntity findByEMail(String eMail) {
        List users = getSession()
                .createQuery("from UserEntity where email=?")
                .setParameter(0, eMail)
                .list();

        if ((users != null) && (users.size() == 1)) return (UserEntity) users.get(0);
        else return null;
    }

    public Long numByRoleCode(String code) {
        List count = getSession()
                .createQuery("select count(*) from UserEntity where role.code=?")
                .setParameter(0, code)
                .list(); //TODO: list?

        if ((count != null) && (count.size() > 0)) return ((Long) count.get(0));
        return null;
    }

    public List<UserEntity> findByRoleCode(String code) {
        List users = getSession()
                .createQuery("from UserEntity where role.code=?")
                .setParameter(0, code)
                .list();

        if ((users != null) && (users.size() > 0)) return ((List<UserEntity>) users);
        else return null;
    }

//    ==================================================================================
//    === UPDATE

    public Integer updateRoleByID(Integer id, RoleEntity role) {
        return getSession()
                .createQuery("update UserEntity set role=? where id=?")
                .setParameter(0, role)
                .setParameter(1, id)
                .executeUpdate();
    }
}