package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionResultEntity;

import java.util.List;

public interface CompositionResultDAO {

    Integer save(CompositionResultEntity result);

//    ==================================================================================
//    === FIND

    CompositionResultEntity findByID(Integer id);

    CompositionResultEntity findByGameAndStatistic(Integer gameID, Integer statisticID);

    CompositionResultEntity findByGameAndComposition(Integer gameID, Integer compositionID);

    List<CompositionResultEntity> findAll();
}
