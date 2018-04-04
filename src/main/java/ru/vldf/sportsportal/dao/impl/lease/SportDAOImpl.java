package ru.vldf.sportsportal.dao.impl.lease;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.lease.SportDAO;
import ru.vldf.sportsportal.model.lease.SportEntity;

import java.util.List;

@Repository
public class SportDAOImpl extends AbstractDAOImpl<SportEntity, Integer> implements SportDAO {
    public SportDAOImpl() {
        super(SportEntity.class);
    }

    @Override
    public Integer save(SportEntity playground) {
        return super.save(playground);
    }

//    ==================================================================================
//    === FIND

    public SportEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<SportEntity> findAll() {
        return super.list();
    }
}
