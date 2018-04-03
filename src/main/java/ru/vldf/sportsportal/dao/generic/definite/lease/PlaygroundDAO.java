package ru.vldf.sportsportal.dao.generic.definite.lease;

import ru.vldf.sportsportal.model.lease.PlaygroundEntity;

import java.util.List;

public interface PlaygroundDAO {

    Integer save(PlaygroundEntity playground);

//    ==================================================================================
//    === FIND

    PlaygroundEntity findByID(Integer id);

    List<PlaygroundEntity> findAll();
}