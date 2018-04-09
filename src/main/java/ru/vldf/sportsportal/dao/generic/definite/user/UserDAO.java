package ru.vldf.sportsportal.dao.generic.definite.user;

import ru.vldf.sportsportal.model.tourney.TeamPlayerEntity;
import ru.vldf.sportsportal.model.user.UserRoleEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

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

    Integer updateRoleByID(Integer id, UserRoleEntity role);

    Integer updateTeamPlayerByID(Integer id, TeamPlayerEntity player);
}