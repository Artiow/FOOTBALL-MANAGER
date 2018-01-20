package ru.vldf.sportsportal.dao.impl;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.SportEntity;

@Repository
public class SportDAO extends GenericDAOImpl<SportEntity, Integer> {
    private SportDAO() {
        super(SportEntity.class);
    }
}