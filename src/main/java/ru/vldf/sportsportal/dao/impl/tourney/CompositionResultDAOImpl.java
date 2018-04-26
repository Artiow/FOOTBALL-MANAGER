package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CompositionResultDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionResultEntity;

import java.util.List;

@Repository
public class CompositionResultDAOImpl extends AbstractDAOImpl<CompositionResultEntity, Integer> implements CompositionResultDAO {
    public CompositionResultDAOImpl() {
        super(CompositionResultEntity.class);
    }

    @Override
    public Integer save(CompositionResultEntity entity) {
        return super.save(entity);
    }

    public CompositionResultEntity findByID(Integer id) {
        return super.get(id);
    }

    public CompositionResultEntity findByGameAndStatistic(Integer gameID, Integer statisticID) {
        List results = getSession()
                .createQuery("from CompositionResultEntity where game.id=:gameID and statistic.id=:statisticID")
                .setParameter("statisticID", statisticID)
                .setParameter("gameID", gameID)
                .list();

        if ((results != null) && (results.size() == 1)) return (CompositionResultEntity) results.get(0);
        else return null;
    }

    public CompositionResultEntity findByGameAndComposition(Integer gameID, Integer compositionID) {
        List results = getSession()
                .createQuery("from CompositionResultEntity where game.id=:gameID and statistic.composition.id=:compositionID")
                .setParameter("compositionID", compositionID)
                .setParameter("gameID", gameID)
                .list();

        if ((results != null) && (results.size() == 1)) return (CompositionResultEntity) results.get(0);
        else return null;
    }

    public List<CompositionResultEntity> findAll() {
        return super.list();
    }
}
