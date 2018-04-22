package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CompositionStatisticDAO;
import ru.vldf.sportsportal.domain.tourney.CompositionStatisticEntity;

import java.util.List;

@Repository
public class CompositionStatisticDAOImpl extends AbstractDAOImpl<CompositionStatisticEntity, Integer> implements CompositionStatisticDAO {
    public CompositionStatisticDAOImpl() {
        super(CompositionStatisticEntity.class);
    }

    @Override
    public Integer save(CompositionStatisticEntity entity) {
        return super.save(entity);
    }

//    ==================================================================================
//    === FIND

    public CompositionStatisticEntity findByID(Integer id) {
        return get(id);
    }

    public List<CompositionStatisticEntity> findAll() {
        return list();
    }
}
