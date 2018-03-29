package ru.vldf.sportsportal.dao.generic;

import ru.vldf.sportsportal.model.RoleEntity;

public interface RoleDAO {
    RoleEntity findByCode(String code);
}
