package ru.vldf.sportsportal.dao.generic.definite.user;

import ru.vldf.sportsportal.model.user.RoleEntity;

public interface RoleDAO {
    RoleEntity findByCode(String code);
}
