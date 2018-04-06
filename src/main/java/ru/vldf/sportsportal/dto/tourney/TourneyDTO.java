package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.model.tourney.TourneyEntity;

public class TourneyDTO {
    private Integer id;
    private String name;

    public TourneyDTO() {

    }

    public TourneyDTO(TourneyEntity tourney) {
        id = tourney.getId();
        name = tourney.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
