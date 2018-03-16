package ru.vldf.sportsportal.dao.generic;

import ru.vldf.sportsportal.model.UserEntity;

public interface UserDAO {
    UserEntity findByEMail(String eMail);
}