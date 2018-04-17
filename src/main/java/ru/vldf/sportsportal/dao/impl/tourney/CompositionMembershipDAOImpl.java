package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CompositionMembershipDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionMembershipEntity;

import java.util.List;

@Repository
public class CompositionMembershipDAOImpl extends AbstractDAOImpl<CompositionMembershipEntity, Integer> implements CompositionMembershipDAO {
    public CompositionMembershipDAOImpl() {
        super(CompositionMembershipEntity.class);
    }

    @Override
    public Integer save(CompositionMembershipEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public CompositionMembershipEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<CompositionMembershipEntity> findAll() {
        return super.list();
    }

//    ==================================================================================
//    === DELETE

    public void deleteByID(Integer id) {
        getSession()
                .createQuery("delete CompositionMembershipEntity where id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    public void deleteByMembership(Integer playerID, Integer compositionID) {
        getSession()
                .createQuery("delete CompositionMembershipEntity where player.id=? and composition.id=?")
                .setParameter(0, playerID)
                .setParameter(1, compositionID)
                .executeUpdate();
    }
}
