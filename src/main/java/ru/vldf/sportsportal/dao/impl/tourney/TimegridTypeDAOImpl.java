package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.TimegridTypeDAO;
import ru.vldf.sportsportal.domain.tourney.TimegridTypeEntity;

import java.util.List;

@Repository
public class TimegridTypeDAOImpl extends AbstractDAOImpl<TimegridTypeEntity, Integer> implements TimegridTypeDAO {
    public TimegridTypeDAOImpl() {
        super(TimegridTypeEntity.class);
    }

    @Override
    public Integer save(TimegridTypeEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public TimegridTypeEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<TimegridTypeEntity> findAll() {
        return super.list();
    }
}
