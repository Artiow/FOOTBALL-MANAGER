package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerResultDAO;
import ru.vldf.sportsportal.domain.tourney.PlayerResultEntity;

import java.util.List;

@Repository
public class PlayerResultDAOImpl extends AbstractDAOImpl<PlayerResultEntity, Integer> implements PlayerResultDAO {
    public PlayerResultDAOImpl() {
        super(PlayerResultEntity.class);
    }

    @Override
    public Integer save(PlayerResultEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public PlayerResultEntity findByID(Integer id) {
        return super.get(id);
    }

    public PlayerResultEntity findByGameAndStatistic(Integer gameID, Integer statisticID) {
        List results = getSession()
                .createQuery("from PlayerResultEntity where result.game.id=:gameID and statistic.id=:statisticID")
                .setParameter("statisticID", statisticID)
                .setParameter("gameID", gameID)
                .list();

        if ((results != null) && (results.size() == 1)) return (PlayerResultEntity) results.get(0);
        else return null;
    }

    public PlayerResultEntity findByGameAndMembership(Integer gameID, Integer membershipID) {
        List results = getSession()
                .createQuery("from PlayerResultEntity where result.game.id=:gameID and statistic.membership.id=:membershipID")
                .setParameter("membershipID", membershipID)
                .setParameter("gameID", gameID)
                .list();

        if ((results != null) && (results.size() == 1)) return (PlayerResultEntity) results.get(0);
        else return null;
    }

    public List<PlayerResultEntity> findAll() {
        return super.list();
    }
}
