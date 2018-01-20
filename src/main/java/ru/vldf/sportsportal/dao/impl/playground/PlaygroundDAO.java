package ru.vldf.sportsportal.dao.impl.playground;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.playground.PlaygroundEntity;

@Repository
public class PlaygroundDAO extends GenericDAOImpl<PlaygroundEntity, Integer> {
    public PlaygroundDAO() {
        super(PlaygroundEntity.class);
    }
}
