package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamCompositionStatusDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionStatusEntity;

import java.util.List;

@Repository
public class TeamCompositionStatusDAOImpl extends AbstractDAOImpl<CompositionStatusEntity, Integer> implements TeamCompositionStatusDAO {
    public TeamCompositionStatusDAOImpl() {
        super(CompositionStatusEntity.class);
    }

    @Override
    public Integer save(CompositionStatusEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public CompositionStatusEntity findByID(Integer id) {
        return super.get(id);
    }

    public CompositionStatusEntity findByCode(String code) {
        List statuses = getSession()
                .createQuery("from CompositionStatusEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((statuses != null) && (statuses.size() > 0)) return (CompositionStatusEntity) statuses.get(0);
        else return null;
    }

    public List<CompositionStatusEntity> findAll() {
        return super.list();
    }
}
