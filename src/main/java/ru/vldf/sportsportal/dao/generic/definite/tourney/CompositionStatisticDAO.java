package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionStatisticEntity;

import java.util.List;

public interface CompositionStatisticDAO {
    Integer save(CompositionStatisticEntity result);

//    ==================================================================================
//    === FIND

    CompositionStatisticEntity findByID(Integer id);

    List<CompositionStatisticEntity> findAll();
}
