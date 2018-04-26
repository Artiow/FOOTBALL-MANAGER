package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerStatisticDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerStatisticEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

import java.util.List;

@Repository
public class PlayerStatisticDAOImpl extends AbstractDAOImpl<PlayerStatisticEntity, Integer> implements PlayerStatisticDAO {
    public PlayerStatisticDAOImpl() {
        super(PlayerStatisticEntity.class);
    }

    @Override
    public Integer save(PlayerStatisticEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public PlayerStatisticEntity findByID(Integer id) {
        return get(id);
    }

    public List<PlayerStatisticEntity> findByTourney(Integer id) {
        List statistics = getSession()
                .createQuery("from PlayerStatisticEntity where membership.composition.tourney.id=?")
                .setParameter(0, id)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<PlayerStatisticEntity>) statistics;
        else return null;
    }

    public List<PlayerStatisticEntity> findByTourney(TourneyEntity tourney) {
        List statistics = getSession()
                .createQuery("from PlayerStatisticEntity where membership.composition.tourney=?")
                .setParameter(0, tourney)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<PlayerStatisticEntity>) statistics;
        else return null;
    }

    public List<PlayerStatisticEntity> findByComposition(Integer id) {
        List statistics = getSession()
                .createQuery("from PlayerStatisticEntity where membership.composition.id=?")
                .setParameter(0, id)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<PlayerStatisticEntity>) statistics;
        else return null;
    }

    public List<PlayerStatisticEntity> findByComposition(CompositionEntity composition) {
        List statistics = getSession()
                .createQuery("from PlayerStatisticEntity where membership.composition=?")
                .setParameter(0, composition)
                .list();

        if ((statistics != null) && (statistics.size() > 0)) return (List<PlayerStatisticEntity>) statistics;
        else return null;
    }

    public List<PlayerStatisticEntity> findAll() {
        return list();
    }
}
