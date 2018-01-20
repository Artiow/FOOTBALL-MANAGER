package ru.vldf.sportsportal.dao.impl.playground;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.playground.PlaygroundSpecializationEntity;
import ru.vldf.sportsportal.model.playground.PlaygroundSpecializationEntityPK;

@Repository
public class PlaygroundSpecializationDAO extends GenericDAOImpl<PlaygroundSpecializationEntity, PlaygroundSpecializationEntityPK> {
    public PlaygroundSpecializationDAO() {
        super(PlaygroundSpecializationEntity.class);
    }
}
