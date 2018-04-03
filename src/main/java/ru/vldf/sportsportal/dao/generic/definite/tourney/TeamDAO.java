package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamEntity;
import ru.vldf.sportsportal.model.tourney.TeamStatusEntity;
import ru.vldf.sportsportal.model.user.UserEntity;

import java.util.List;

public interface TeamDAO {

    Integer save(TeamEntity team);

//    ==================================================================================
//    === FIND

    List<TeamEntity> findByUser(Integer userID);

    List<TeamEntity> findByUser(String userLogin);

    List<TeamEntity> findByUser(UserEntity user);

    List<TeamEntity> findByStatus(Integer statusID);

    List<TeamEntity> findByStatus(String statusCode);

    List<TeamEntity> findByStatus(TeamStatusEntity status);
}
