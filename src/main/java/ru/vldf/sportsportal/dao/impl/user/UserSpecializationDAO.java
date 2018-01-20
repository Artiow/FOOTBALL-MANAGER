package ru.vldf.sportsportal.dao.impl.user;

import org.springframework.stereotype.Repository;
import ru.vldf.sportsportal.dao.generic.GenericDAOImpl;
import ru.vldf.sportsportal.model.user.UserSpecializationEntity;
import ru.vldf.sportsportal.model.user.UserSpecializationEntityPK;

@Repository
public class UserSpecializationDAO extends GenericDAOImpl<UserSpecializationEntity, UserSpecializationEntityPK>{
    public UserSpecializationDAO () {
        super(UserSpecializationEntity.class);
    }
}
