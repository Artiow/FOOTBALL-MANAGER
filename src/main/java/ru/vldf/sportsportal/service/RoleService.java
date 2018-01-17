package ru.vldf.sportsportal.service;

import ru.vldf.sportsportal.model.RoleEntity;
import ru.vldf.sportsportal.dao.impl.RoleDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Transactional
@Service("RoleService")
public class RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public List<RoleEntity> listRoles() {
        return roleDAO.list();
    }
}
