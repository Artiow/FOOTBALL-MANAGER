package ru.vldf.sportsportal.dao.impl.user;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

@Repository
public class UserDAO extends GenericDAOImpl<UserEntity, Integer> {

    public UserDAO() {
        super(UserEntity.class);
    }

    public List<UserEntity> findByName(String name, String surname) {
        List<UserEntity> users = getSession()
                .createQuery("from UserEntity where name=? and surname=?")
                .setParameter(0, name).setParameter(1, surname)
                .list();

        if ((users != null) && (users.size() > 0)) return users;
        else return null;
    }

    public UserEntity findByEMail(String eMail) {
        List<UserEntity> users = getSession()
                .createQuery("from UserEntity where email=?")
                .setParameter(0, eMail)
                .list();

        if ((users != null) && (users.size() > 0)) return users.get(0);
        else return null;
    }
}