package ru.vldf.sportsportal.dao.generic.definite.lease;

import ru.vldf.sportsportal.model.lease.PlaygroundEntity;

import java.util.List;

public interface PlaygroundDAO {
    PlaygroundEntity findPlaygroundByID(Integer id);

    List<PlaygroundEntity> getPlaygroundList();
}