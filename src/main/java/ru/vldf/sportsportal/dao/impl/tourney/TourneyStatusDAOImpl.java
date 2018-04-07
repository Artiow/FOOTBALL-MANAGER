package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourneyStatusDAO;
import ru.vldf.sportsportal.model.tourney.TourneyStatusEntity;

import java.util.List;

@Repository
public class TourneyStatusDAOImpl extends AbstractDAOImpl<TourneyStatusEntity, Integer> implements TourneyStatusDAO {
    public TourneyStatusDAOImpl() {
        super(TourneyStatusEntity.class);
    }

    @Override
    public Integer save(TourneyStatusEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TourneyStatusEntity findByID(Integer id) {
        return super.get(id);
    }

    public TourneyStatusEntity findByCode(String code) {
        List statuses = getSession()
                .createQuery("from TeamStatusEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((statuses != null) && (statuses.size() > 0)) return (TourneyStatusEntity) statuses.get(0);
        else return null;
    }

    public List<TourneyStatusEntity> findAll() {
        return super.list();
    }
}
