package ru.vldf.sportsportal.dao.impl.tourney;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.abstrct.AbstractDAOImpl;
import ru.vldf.sportsportal.dao.generic.definite.tourney.CardDAO;
import ru.vldf.sportsportal.domain.tourney.CardEntity;

import java.util.List;

@Repository
public class CardDAOImpl extends AbstractDAOImpl<CardEntity, Integer> implements CardDAO {
    public CardDAOImpl() {
        super(CardEntity.class);
    }

    @Override
    public Integer save(CardEntity entity) {
        return super.save(entity);
    }

    public CardEntity findByID(Integer id) {
        return super.get(id);
    }

    public CardEntity findByCode(String code) {
        List cards = getSession()
                .createQuery("from CardEntity where code=?")
                .setParameter(0, code)
                .list();

        if ((cards != null) && (cards.size() > 0)) return (CardEntity) cards.get(0);
        else return null;
    }

    public List<CardEntity> findAll() {
        return super.list();
    }
}
