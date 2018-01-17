package ru.vldf.sportsportal.service;

import ru.vldf.sportsportal.model.UserEntity;
import ru.vldf.sportsportal.dao.impl.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserEntity> listUsers() {
        return userDAO.list();
    }
}