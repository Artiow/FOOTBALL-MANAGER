package ru.vldf.sportsportal.dao.generic.definite.user;

import ru.vldf.sportsportal.model.user.RoleEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

public interface UserDAO {

    Integer saveUser(UserEntity user);

//    ==================================================================================
//    === FIND

    UserEntity findByID(Integer id);

    UserEntity findByLogin(String login);

    UserEntity findByEMail(String eMail);

    Long numByRoleCode(String code);

    List<UserEntity> findByRoleCode(String code);

//    ==================================================================================
//    === UPDATE

    Integer updateRoleByID(Integer id, RoleEntity role);
}