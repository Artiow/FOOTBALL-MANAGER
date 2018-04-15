package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamPlayerDAO;
import ru.vldf.sportsportal.model.tourney.TeamCompositionEntity;
import ru.vldf.sportsportal.model.tourney.TeamPlayerEntity;

import java.util.List;

@Repository
public class TeamPlayerDAOImpl extends AbstractDAOImpl<TeamPlayerEntity, Integer> implements TeamPlayerDAO {
    public TeamPlayerDAOImpl() {
        super(TeamPlayerEntity.class);
    }

    @Override
    public Integer save(TeamPlayerEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TeamPlayerEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TeamPlayerEntity> findByFullName(String name, String surname, String patronymic) {
        List players = getSession()
                .createQuery("from TeamPlayerEntity where name=? and surname=? and  patronymic=?")
                .setParameter(0, name)
                .setParameter(1, surname)
                .setParameter(2, patronymic)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<TeamPlayerEntity>) players;
        else return null;
    }

    public List<TeamPlayerEntity> findByTeamComposition(Integer compositionID) {
        List players = getSession()
                .createQuery("select ePlayer from TeamPlayerEntity as ePlayer"
                        + " join ePlayer.memberships as eMembership"
                        + " join eMembership.composition as eComposition"
                        + " with eComposition.id=?")
                .setParameter(0, compositionID)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<TeamPlayerEntity>) players;
        else return null;
    }

//    ==================================================================================
//    === DELETE

    public void deleteByID(Integer id) {
        getSession()
                .createQuery("delete TeamPlayerEntity where id=?")
                .setParameter(0, id)
                .executeUpdate();
    }
}
