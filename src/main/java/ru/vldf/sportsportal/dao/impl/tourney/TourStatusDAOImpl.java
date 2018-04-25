package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourStatusDAO;
import ru.vldf.sportsportal.domain.tourney.TourStatusEntity;

import java.util.List;

@Repository
public class TourStatusDAOImpl extends AbstractDAOImpl<TourStatusEntity, Integer> implements TourStatusDAO {
    public TourStatusDAOImpl() {
        super(TourStatusEntity.class);
    }

    @Override
    public Integer save(TourStatusEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TourStatusEntity findByID(Integer id) {
        return super.get(id);
    }

    public TourStatusEntity findByCode(String code) {
        List statuses = getSession()
                .createQuery("from TourStatusEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((statuses != null) && (statuses.size() == 1)) return (TourStatusEntity) statuses.get(0);
        else return null;
    }

    public List<TourStatusEntity> findAll() {
        return super.list();
    }
}
