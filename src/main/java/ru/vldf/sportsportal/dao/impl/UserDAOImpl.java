package ru.vldf.sportsportal.dao.impl;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.UserDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.model.UserEntity;

import java.util.List;

@Repository
public class UserDAOImpl extends AbstractDAOImpl<UserEntity, Integer> implements UserDAO {
    public UserDAOImpl() {
        super(UserEntity.class);
    }

    public UserEntity findByEMail(String eMail) {
        List users = getSession()
                .createQuery("from UserEntity where email=?")
                .setParameter(0, eMail)
                .list();

        if ((users != null) && (users.size() > 0)) return (UserEntity) users.get(0);
        else return null;
    }
}