package ru.vldf.sportsportal.dao.impl.lease;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.definite.lease.PlaygroundDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.model.lease.PlaygroundEntity;

import java.util.List;

@Repository
public class PlaygroundDAOImpl extends AbstractDAOImpl<PlaygroundEntity, Integer> implements PlaygroundDAO {
    public PlaygroundDAOImpl() {
        super(PlaygroundEntity.class);
    }

    public PlaygroundEntity findPlaygroundByID(Integer id) {
        return this.get(id);
    }

    public List<PlaygroundEntity> getPlaygroundList() {
        return this.list();
    }
}