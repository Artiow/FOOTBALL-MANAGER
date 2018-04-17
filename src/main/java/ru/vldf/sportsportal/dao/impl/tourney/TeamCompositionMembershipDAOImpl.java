package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamCompositionMembershipDAO;
import ru.vldf.sportsportal.domain.tourney.TeamCompositionMembershipEntity;

import java.util.List;

@Repository
public class TeamCompositionMembershipDAOImpl extends AbstractDAOImpl<TeamCompositionMembershipEntity, Integer> implements TeamCompositionMembershipDAO {
    public TeamCompositionMembershipDAOImpl() {
        super(TeamCompositionMembershipEntity.class);
    }

    @Override
    public Integer save(TeamCompositionMembershipEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TeamCompositionMembershipEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TeamCompositionMembershipEntity> findAll() {
        return super.list();
    }

//    ==================================================================================
//    === DELETE

    public void deleteByID(Integer id) {
        getSession()
                .createQuery("delete TeamCompositionMembershipEntity where id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    public void deleteByMembership(Integer playerID, Integer compositionID) {
        getSession()
                .createQuery("delete TeamCompositionMembershipEntity where player.id=? and composition.id=?")
                .setParameter(0, playerID)
                .setParameter(1, compositionID)
                .executeUpdate();
    }
}
