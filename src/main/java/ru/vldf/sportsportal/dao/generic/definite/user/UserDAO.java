package ru.vldf.sportsportal.dao.generic.definite.user;

import ru.vldf.sportsportal.model.user.UserEntity;

public interface UserDAO {
    Integer saveUser(UserEntity user);

    UserEntity findByID(Integer id);

    UserEntity findByLogin(String login);

    UserEntity findByEMail(String eMail);
}