package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CompositionStatisticDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionStatisticEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

@Repository
public class CompositionStatisticDAOImpl extends AbstractDAOImpl<CompositionStatisticEntity, Integer> implements CompositionStatisticDAO {
    public CompositionStatisticDAOImpl() {
        super(CompositionStatisticEntity.class);
    }

    @Override
    public Integer save(CompositionStatisticEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public CompositionStatisticEntity findByID(Integer id) {
        return get(id);
    }

    public List<CompositionStatisticEntity> findByTourney(Integer id) {
        List statistics = getSession()
                .createQuery("from CompositionStatisticEntity where composition.tourney.id=?"
                        + " order by score desc, winNum desc, diff desc, cloggedNum desc, gameNum desc"
                ).setParameter(0, id)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<CompositionStatisticEntity>) statistics;
        else return null;
    }

    public List<CompositionStatisticEntity> findByTourney(TourneyEntity tourney) {
        List statistics = getSession()
                .createQuery("from CompositionStatisticEntity where composition.tourney=?"
                        + " order by score desc, winNum desc, diff desc, cloggedNum desc, gameNum desc"
                ).setParameter(0, tourney)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<CompositionStatisticEntity>) statistics;
        else return null;
    }

    public List<CompositionStatisticEntity> findByComposition(Integer id) {
        List statistics = getSession()
                .createQuery("from CompositionStatisticEntity where composition.id=?"
                        + " order by score desc, winNum desc, diff desc, cloggedNum desc, gameNum desc"
                ).setParameter(0, id)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<CompositionStatisticEntity>) statistics;
        else return null;
    }

    public List<CompositionStatisticEntity> findByComposition(CompositionEntity composition) {
        List statistics = getSession()
                .createQuery("from CompositionStatisticEntity where composition=?"
                        + " order by score desc, winNum desc, diff desc, cloggedNum desc, gameNum desc"
                ).setParameter(0, composition)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<CompositionStatisticEntity>) statistics;
        else return null;
    }

    public List<CompositionStatisticEntity> findAll() {
        return list();
    }
}
