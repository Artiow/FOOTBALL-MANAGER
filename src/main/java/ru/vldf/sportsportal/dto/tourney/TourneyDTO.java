package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

public class TourneyDTO {
    private Integer id;
    private String name;

    private TourneyStatusDTO status;

    public TourneyDTO() {

    }

    public TourneyDTO(TourneyEntity tourney) {
        id = tourney.getId();
        name = tourney.getName();

        if (tourney.getTourneyStatus() != null) status = new TourneyStatusDTO(tourney.getTourneyStatus());
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

    public TourneyStatusDTO getStatus() {
        return status;
    }

    public void setStatus(TourneyStatusDTO status) {
        this.status = status;
    }
}
