package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.impl.SportDAO;
import ru.vldf.sportsportal.model.SportEntity;

import java.util.List;

@Service
public class SportService {
    private SportDAO sportDAO;

    @Autowired
    public void setSportDAO(SportDAO sportDAO) {
        this.sportDAO = sportDAO;
    }

    @Transactional(readOnly = true)
    public List<SportEntity> listSports() {
        return sportDAO.list();
    }
}
