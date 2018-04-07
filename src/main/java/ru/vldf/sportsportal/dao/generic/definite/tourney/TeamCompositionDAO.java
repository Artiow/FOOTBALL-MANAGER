package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;

import java.util.List;

public interface TeamCompositionDAO {

    Integer save(TeamCompositionEntity composition);

//    ==================================================================================
//    === FIND

    TeamCompositionEntity findByID(Integer id);

    List<TeamCompositionEntity> findAll();
}
