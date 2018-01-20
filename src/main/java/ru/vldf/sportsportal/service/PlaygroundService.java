package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.impl.playground.PlaygroundDAO;
import ru.vldf.sportsportal.model.playground.PlaygroundEntity;

import java.util.List;

@Service
public class PlaygroundService {
    private PlaygroundDAO playgroundDAO;

    @Autowired
    public void setPlaygroundDAO(PlaygroundDAO playgroundDAO) {
        this.playgroundDAO = playgroundDAO;
    }

    @Transactional(readOnly = true)
    public List<PlaygroundEntity> listPlaygrounds() {
        return playgroundDAO.list();
    }
}
