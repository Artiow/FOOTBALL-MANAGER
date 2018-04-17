package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionMembershipEntity;

import java.util.List;

public interface TeamCompositionMembershipDAO {
    Integer save(CompositionMembershipEntity composition);

//    ==================================================================================
//    === FIND

    CompositionMembershipEntity findByID(Integer id);

    List<CompositionMembershipEntity> findAll();

//    ==================================================================================
//    === DELETE

    void deleteByID(Integer id);

    void deleteByMembership(Integer playerID, Integer compositionID);

}
