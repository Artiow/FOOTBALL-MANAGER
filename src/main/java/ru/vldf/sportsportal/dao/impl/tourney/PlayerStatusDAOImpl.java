package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerStatusDAO;
import ru.vldf.sportsportal.domain.tourney.PlayerStatusEntity;

import java.util.List;

@Repository
public class PlayerStatusDAOImpl extends AbstractDAOImpl<PlayerStatusEntity, Integer> implements PlayerStatusDAO {
    public PlayerStatusDAOImpl() {
        super(PlayerStatusEntity.class);
    }

    @Override
    public Integer save(PlayerStatusEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public PlayerStatusEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<PlayerStatusEntity> findAll() {
        return super.list();
    }
}
