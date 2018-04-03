package ru.vldf.sportsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vldf.sportsportal.dao.generic.definite.lease.PlaygroundDAO;
import ru.vldf.sportsportal.dto.lease.PlaygroundDTO;
import ru.vldf.sportsportal.model.lease.PlaygroundEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseService {
    private PlaygroundDAO playgroundDAO;

    @Autowired
    public void setPlaygroundDAO(PlaygroundDAO playgroundDAO) {
        this.playgroundDAO = playgroundDAO;
    }

    @Transactional(readOnly = true)
    public List<PlaygroundDTO> getPlaygroundList() {
        List<PlaygroundEntity> entityList = playgroundDAO.findAll();
        List<PlaygroundDTO> dtoList = new ArrayList<PlaygroundDTO>();

        for (PlaygroundEntity entity: entityList) dtoList.add(new PlaygroundDTO(entity));
        return dtoList;
    }

    @Transactional(readOnly = true)
    public PlaygroundDTO getPlayground(Integer id) {
        return new PlaygroundDTO(playgroundDAO.findByID(id));
    }
}