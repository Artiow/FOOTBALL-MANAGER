package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamCompositionStatusDAO;
import ru.vldf.sportsportal.domain.tourney.TeamCompositionStatusEntity;

import java.util.List;

@Repository
public class TeamCompositionStatusDAOImpl extends AbstractDAOImpl<TeamCompositionStatusEntity, Integer> implements TeamCompositionStatusDAO {
    public TeamCompositionStatusDAOImpl() {
        super(TeamCompositionStatusEntity.class);
    }

    @Override
    public Integer save(TeamCompositionStatusEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TeamCompositionStatusEntity findByID(Integer id) {
        return super.get(id);
    }

    public TeamCompositionStatusEntity findByCode(String code) {
        List statuses = getSession()
                .createQuery("from TeamCompositionStatusEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((statuses != null) && (statuses.size() > 0)) return (TeamCompositionStatusEntity) statuses.get(0);
        else return null;
    }

    public List<TeamCompositionStatusEntity> findAll() {
        return super.list();
    }
}
