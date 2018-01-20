package ru.vldf.sportsportal.dao.impl.user;

import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.user.RoleEntity;

import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends GenericDAOImpl<RoleEntity, Integer>{
    public RoleDAO() {
        super(RoleEntity.class);
    }
}