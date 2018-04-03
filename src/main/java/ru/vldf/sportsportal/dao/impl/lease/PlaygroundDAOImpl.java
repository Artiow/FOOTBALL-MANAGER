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

    @Override
    public Integer save(PlaygroundEntity playground) {
        return super.save(playground);
    }

//    ==================================================================================
//    === FIND

    public PlaygroundEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<PlaygroundEntity> findAll() {
        return super.list();
    }
}