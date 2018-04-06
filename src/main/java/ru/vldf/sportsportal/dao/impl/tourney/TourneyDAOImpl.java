package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TourneyDAO;
import ru.vldf.sportsportal.model.tourney.TourneyEntity;

import java.util.List;

@Repository
public class TourneyDAOImpl extends AbstractDAOImpl<TourneyEntity, Integer> implements TourneyDAO {
    public TourneyDAOImpl() {
        super(TourneyEntity.class);
    }

    @Override
    public Integer save(TourneyEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TourneyEntity findByID(Integer id) {
        return super.get(id);
    }

    public Long numAll() {
        List count = getSession()
                .createQuery("select count(*) from TourneyEntity")
                .list(); //TODO: list?

        if ((count != null) && (count.size() > 0)) return ((Long) count.get(0));
        else return null;
    }

    public List<TourneyEntity> findAll() {
        return super.list();
    }
}