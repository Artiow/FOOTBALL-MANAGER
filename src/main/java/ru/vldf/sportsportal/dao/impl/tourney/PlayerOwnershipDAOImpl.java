package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.PlayerOwnershipDAO;
import ru.vldf.sportsportal.domain.tourney.PlayerOwnershipEntity;

import java.util.List;

@Repository
public class PlayerOwnershipDAOImpl extends AbstractDAOImpl<PlayerOwnershipEntity, Integer> implements PlayerOwnershipDAO {
    public PlayerOwnershipDAOImpl() {
        super(PlayerOwnershipEntity.class);
    }

    @Override
    public Integer save(PlayerOwnershipEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public PlayerOwnershipEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<PlayerOwnershipEntity> findAll() {
        return super.list();
    }
}
