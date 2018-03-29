package ru.vldf.sportsportal.dao.generic;

import ru.vldf.sportsportal.model.UserEntity;

public interface UserDAO {
    Integer saveUser(UserEntity user);

    UserEntity findByEMail(String eMail);
}