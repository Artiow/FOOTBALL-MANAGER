package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.ResultPlayerDAO;
import ru.vldf.sportsportal.domain.tourney.ResultPlayerEntity;

import java.util.List;

@Repository
public class ResultPlayerDAOImpl extends AbstractDAOImpl<ResultPlayerEntity, Integer> implements ResultPlayerDAO {
    public ResultPlayerDAOImpl() {
        super(ResultPlayerEntity.class);
    }

    @Override
    public Integer save(ResultPlayerEntity entity) {
        return super.save(entity);
    }

    public ResultPlayerEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<ResultPlayerEntity> findAll() {
        return super.list();
    }
}
