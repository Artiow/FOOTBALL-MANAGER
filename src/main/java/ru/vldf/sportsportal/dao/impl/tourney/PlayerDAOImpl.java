package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerDAO;
import ru.vldf.sportsportal.domain.tourney.PlayerEntity;

import java.util.List;

@Repository
public class PlayerDAOImpl extends AbstractDAOImpl<PlayerEntity, Integer> implements PlayerDAO {
    public PlayerDAOImpl() {
        super(PlayerEntity.class);
    }

    @Override
    public Integer save(PlayerEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public PlayerEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<PlayerEntity> findByFullName(String name, String surname, String patronymic) {
        List players = getSession()
                .createQuery("from PlayerEntity where name=? and surname=? and  patronymic=?")
                .setParameter(0, name)
                .setParameter(1, surname)
                .setParameter(2, patronymic)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<PlayerEntity>) players;
        else return null;
    }

    public List<PlayerEntity> findByTeamComposition(Integer compositionID) {
        List players = getSession()
                .createQuery("select ePlayer from PlayerEntity as ePlayer"
                        + " join ePlayer.memberships as eMembership"
                        + " join eMembership.composition as eComposition"
                        + " with eComposition.id=?")
                .setParameter(0, compositionID)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<PlayerEntity>) players;
        else return null;
    }

//    ==================================================================================
//    === DELETE

    public void deleteByID(Integer id) {
        getSession()
                .createQuery("delete PlayerEntity where id=?")
                .setParameter(0, id)
                .executeUpdate();
    }
}
