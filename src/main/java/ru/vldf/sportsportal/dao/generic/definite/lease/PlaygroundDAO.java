package ru.vldf.sportsportal.dao.generic.definite.lease;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;

import java.util.List;

public interface PlaygroundDAO {

    Integer save(PlaygroundEntity playground);

//    ==================================================================================
//    === FIND

    PlaygroundEntity findByID(Integer id);

    PlaygroundEntity findByName(String name);

    PlaygroundEntity findByAddress(String address);

    List<PlaygroundEntity> findAll();
}