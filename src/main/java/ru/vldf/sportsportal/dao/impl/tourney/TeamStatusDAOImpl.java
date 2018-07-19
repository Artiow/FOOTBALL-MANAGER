package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TeamStatusDAO;
import ru.vldf.sportsportal.domain.tourney.TeamStatusEntity;

import java.util.List;

@Repository
public class TeamStatusDAOImpl extends AbstractDAOImpl<TeamStatusEntity, Integer> implements TeamStatusDAO {
    public TeamStatusDAOImpl() {
        super(TeamStatusEntity.class);
    }

    @Override
    public Integer save(TeamStatusEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TeamStatusEntity findByID(Integer id) {
        return super.get(id);
    }

    public TeamStatusEntity findByCode(String code) {
        List statuses = getSession()
                .createQuery("from TeamStatusEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((statuses != null) && (statuses.size() == 1)) return (TeamStatusEntity) statuses.get(0);
        else return null;
    }

    public List<TeamStatusEntity> findAll() {
        return super.list();
    }
}