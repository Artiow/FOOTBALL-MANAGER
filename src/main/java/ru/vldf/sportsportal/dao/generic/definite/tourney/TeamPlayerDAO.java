package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.model.tourney.TeamPlayerEntity;

import java.util.List;

public interface TeamPlayerDAO {
    Integer save(TeamPlayerEntity player);

//    ==================================================================================
//    === FIND

    TeamPlayerEntity findByID(Integer id);

    List<TeamPlayerEntity> findByFullName(String name, String surname, String patronymic);

    List<TeamPlayerEntity> findByTeamComposition(Integer compositionID);

//    ==================================================================================
//    === DELETE

    void deleteByID(Integer id);
}
