package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamCompositionMembershipEntity;

import java.util.List;

public interface TeamCompositionMembershipDAO {
    Integer save(TeamCompositionMembershipEntity composition);

//    ==================================================================================
//    === FIND

    TeamCompositionMembershipEntity findByID(Integer id);

    List<TeamCompositionMembershipEntity> findAll();

//    ==================================================================================
//    === DELETE

    void deleteByID(Integer id);

    void deleteByMembership(Integer playerID, Integer compositionID);

}
