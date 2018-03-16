package ru.vldf.sportsportal.dao.generic;

import ru.vldf.sportsportal.model.PlaygroundEntity;

import java.util.List;

public interface PlaygroundDAO {
    PlaygroundEntity findPlaygroundByID(Integer id);

    List<PlaygroundEntity> getPlaygroundList();
}