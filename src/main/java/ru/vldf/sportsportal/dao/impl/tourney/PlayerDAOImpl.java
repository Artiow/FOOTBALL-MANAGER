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

    public List<PlayerEntity> findBySurname(String surname) {
        List players = getSession()
                .createQuery("from PlayerEntity where surname=?")
                .setParameter(0, surname)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<PlayerEntity>) players;
        else return null;
    }

    public List<PlayerEntity> findBySurnameAndName(String surname, String name) {
        List players = getSession()
                .createQuery("from PlayerEntity where surname=? and name=?")
                .setParameter(0, surname)
                .setParameter(1, name)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<PlayerEntity>) players;
        else return null;
    }

    public List<PlayerEntity> findBySurnameAndPatronymic(String surname, String patronymic) {
        List players = getSession()
                .createQuery("from PlayerEntity where surname=? and patronymic=?")
                .setParameter(0, surname)
                .setParameter(1, patronymic)
                .list();

        if ((players != null) && (players.size() > 0)) return (List<PlayerEntity>) players;
        else return null;
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
                .createQuery("select p from PlayerEntity as p"
                        + " join p.memberships as m"
                        + " join m.composition as c"
                        + " with c.id=?"
                ).setParameter(0, compositionID)
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
