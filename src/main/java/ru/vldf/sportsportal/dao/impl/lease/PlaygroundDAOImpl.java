package ru.vldf.sportsportal.dao.impl.lease;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.definite.lease.PlaygroundDAO;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;

import java.util.List;

@Repository
public class PlaygroundDAOImpl extends AbstractDAOImpl<PlaygroundEntity, Integer> implements PlaygroundDAO {
    public PlaygroundDAOImpl() {
        super(PlaygroundEntity.class);
    }

    @Override
    public Integer save(PlaygroundEntity playground) {
        return super.save(playground);
    }

//    ==================================================================================
//    === FIND

    public PlaygroundEntity findByID(Integer id) {
        return super.get(id);
    }

    public PlaygroundEntity findByName(String name) {
        List playgrounds = getSession()
                .createQuery("from PlaygroundEntity where name=?")
                .setParameter(0, name)
                .list();

        if ((playgrounds != null) && (playgrounds.size() == 1)) return (PlaygroundEntity) playgrounds.get(0);
        else return null;
    }

    public PlaygroundEntity findByAddress(String address) {
        List playgrounds = getSession()
                .createQuery("from PlaygroundEntity where address=?")
                .setParameter(0, address)
                .list();

        if ((playgrounds != null) && (playgrounds.size() == 1)) return (PlaygroundEntity) playgrounds.get(0);
        else return null;
    }

    public List<PlaygroundEntity> findAll() {
        return super.list();
    }
}