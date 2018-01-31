package ru.vldf.sportsportal.dao.impl;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.PlaygroundEntity;

@Repository
public class PlaygroundDAO extends GenericDAOImpl<PlaygroundEntity, Integer> {
    public PlaygroundDAO() {
        super(PlaygroundEntity.class);
    }
}
