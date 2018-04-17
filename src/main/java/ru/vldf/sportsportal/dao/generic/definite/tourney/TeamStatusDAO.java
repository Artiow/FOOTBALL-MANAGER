package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamStatusEntity;

import java.util.List;

public interface TeamStatusDAO {

    Integer save(TeamStatusEntity status);

//    ==================================================================================
//    === FIND

    TeamStatusEntity findByID(Integer id);

    TeamStatusEntity findByCode(String code);

    List<TeamStatusEntity> findAll();
}
