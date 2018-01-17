package ru.vldf.sportsportal.dao.impl;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.UserEntity;

@Repository("UserDAO")
public class UserDAO extends GenericDAOImpl<UserEntity, Integer> {
    public UserDAO() {
        super(UserEntity.class);
    }
}
