package ru.vldf.sportsportal.dao.generic.definite.common;

import ru.vldf.sportsportal.domain.common.UserRoleEntity;
import ru.vldf.sportsportal.domain.common.UserEntity;

import java.util.List;

public interface UserDAO {

    Integer save(UserEntity user);

//    ==================================================================================
//    === FIND

    UserEntity findByID(Integer id);

    UserEntity findByLogin(String login);

    UserEntity findByEMail(String eMail);

    Long numByRole(Integer roleID);

    Long numByRole(String roleCode);

    Long numByRole(UserRoleEntity role);

    List<UserEntity> findByRole(Integer roleID);

    List<UserEntity> findByRole(String roleCode);

    List<UserEntity> findByRole(UserRoleEntity role);

//    ==================================================================================
//    === UPDATE

    Integer updateRoleByID(Integer id, Integer roleID);

    Integer updateRoleByID(Integer id, String roleCode);

    Integer updateRoleByID(Integer id, UserRoleEntity role);
}