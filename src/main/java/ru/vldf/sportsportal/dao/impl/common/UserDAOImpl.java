package ru.vldf.sportsportal.dao.impl.common;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.definite.common.UserDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.domain.tourney.PlayerEntity;
import ru.vldf.sportsportal.domain.common.UserRoleEntity;
import ru.vldf.sportsportal.domain.common.UserEntity;

import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<UserEntity, Integer> implements UserDAO {
    public UserDAOImpl() {
        super(UserEntity.class);
    }

    @Override
    public Integer save(UserEntity user) {
        return super.save(user);
    }

//    ==================================================================================
//    === FIND

    public UserEntity findByID(Integer id) {
        return super.get(id);
    }

    public UserEntity findByLogin(String login) {
        List users = getSession()
                .createQuery("from UserEntity where login=lower(?)")
                .setParameter(0, login)
                .list();

        if ((users != null) && (users.size() == 1)) return (UserEntity) users.get(0);
        else return null;
    }

    public UserEntity findByEMail(String eMail) {
        List users = getSession()
                .createQuery("from UserEntity where email=lower(?)")
                .setParameter(0, eMail)
                .list();

        if ((users != null) && (users.size() == 1)) return (UserEntity) users.get(0);
        else return null;
    }

    public Long numByRole(Integer roleID) {
        List count = getSession()
                .createQuery("select count(*) from UserEntity where role.id=?")
                .setParameter(0, roleID)
                .list();

        if ((count != null) && (count.size() == 1)) return ((Long) count.get(0));
        else return null;
    }

    public Long numByRole(String roleCode) {
        List count = getSession()
                .createQuery("select count(*) from UserEntity where role.code=?")
                .setParameter(0, roleCode)
                .list();

        if ((count != null) && (count.size() == 1)) return ((Long) count.get(0));
        else return null;
    }

    public Long numByRole(UserRoleEntity role) {
        List count = getSession()
                .createQuery("select count(*) from UserEntity where role=?")
                .setParameter(0, role)
                .list(); //TODO: list?

        if ((count != null) && (count.size() == 1)) return ((Long) count.get(0));
        else return null;
    }

    public List<UserEntity> findByRole(Integer roleID) {
        List users = getSession()
                .createQuery("from UserEntity where role.id=?")
                .setParameter(0, roleID)
                .list();

        if ((users != null) && (users.size() > 0)) return ((List<UserEntity>) users);
        else return null;
    }

    public List<UserEntity> findByRole(String roleCode) {
        List users = getSession()
                .createQuery("from UserEntity where role.code=?")
                .setParameter(0, roleCode)
                .list();

        if ((users != null) && (users.size() > 0)) return ((List<UserEntity>) users);
        else return null;
    }

    public List<UserEntity> findByRole(UserRoleEntity role) {
        List users = getSession()
                .createQuery("from UserEntity where role=?")
                .setParameter(0, role)
                .list();

        if ((users != null) && (users.size() > 0)) return ((List<UserEntity>) users);
        else return null;
    }

//    ==================================================================================
//    === UPDATE

    public void updateRoleByID(Integer id, Integer roleID) {
        getSession()
                .createQuery("update UserEntity set role = (from UserRoleEntity as r where r.id=:roleID) where id=:id")
                .setParameter("roleID", roleID)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void updateRoleByID(Integer id, String roleCode) {
        getSession()
                .createQuery("update UserEntity set role = (from UserRoleEntity as r where r.code=:roleCode) where id=:id")
                .setParameter("roleCode", roleCode)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void updateRoleByID(Integer id, UserRoleEntity role) {
        getSession()
                .createQuery("update UserEntity set role=:role where id=:id")
                .setParameter("role", role)
                .setParameter("id", id)
                .executeUpdate();
    }
}