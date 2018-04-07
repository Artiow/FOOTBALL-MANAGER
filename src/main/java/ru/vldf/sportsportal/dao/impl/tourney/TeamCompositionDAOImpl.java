package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamCompositionDAO;
import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;

import java.util.List;

@Repository
public class TeamCompositionDAOImpl extends AbstractDAOImpl<TeamCompositionEntity, Integer> implements TeamCompositionDAO {
    public TeamCompositionDAOImpl() {
        super(TeamCompositionEntity.class);
    }

    @Override
    public Integer save(TeamCompositionEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TeamCompositionEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TeamCompositionEntity> findAll() {
        return super.list();
    }
}
