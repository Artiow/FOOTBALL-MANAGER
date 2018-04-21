package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.ResultTeamDAO;
import ru.vldf.sportsportal.domain.tourney.ResultTeamEntity;

import java.util.List;

@Repository
public class ResultTeamDAOImpl extends AbstractDAOImpl<ResultTeamEntity, Integer> implements ResultTeamDAO {
    public ResultTeamDAOImpl() {
        super(ResultTeamEntity.class);
    }

    @Override
    public Integer save(ResultTeamEntity entity) {
        return super.save(entity);
    }

    public ResultTeamEntity findByID(Integer id) {
        return super.get(id);
    }

    public List<ResultTeamEntity> findAll() {
        return super.list();
    }
}
