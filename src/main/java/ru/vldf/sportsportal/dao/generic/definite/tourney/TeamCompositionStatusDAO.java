package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.TeamCompositionStatusEntity;

import java.util.List;

public interface TeamCompositionStatusDAO {

    Integer save(TeamCompositionStatusEntity status);

//    ==================================================================================
//    === FIND

    TeamCompositionStatusEntity findByID(Integer id);

    TeamCompositionStatusEntity findByCode(String code);

    List<TeamCompositionStatusEntity> findAll();
}
