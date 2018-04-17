package ru.vldf.sportsportal.dao.generic.definite.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionStatusEntity;

import java.util.List;

public interface CompositionStatusDAO {

    Integer save(CompositionStatusEntity status);

//    ==================================================================================
//    === FIND

    CompositionStatusEntity findByID(Integer id);

    CompositionStatusEntity findByCode(String code);

    List<CompositionStatusEntity> findAll();
}
