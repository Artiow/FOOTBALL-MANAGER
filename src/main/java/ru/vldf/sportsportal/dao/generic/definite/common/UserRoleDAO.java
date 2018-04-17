package ru.vldf.sportsportal.dao.generic.definite.common;

import ru.vldf.sportsportal.domain.common.UserRoleEntity;

public interface UserRoleDAO {

    Integer save(UserRoleEntity user);

//    ==================================================================================
//    === FIND

    UserRoleEntity findByID(Integer id);

    UserRoleEntity findByCode(String code);
}
