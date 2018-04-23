package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

public class TourneyDTO {
    private Integer id;
    private String name;
    private Integer currentTour = 0;
    private Integer nextTour = 1;

    private TourneyStatusDTO status;

    public TourneyDTO() {

    }

    public TourneyDTO(TourneyEntity tourney) {
        id = tourney.getId();
        name = tourney.getName();
        currentTour = tourney.getCurrentTour();
        nextTour = tourney.getNextTour();

        if (tourney.getStatus() != null) status = new TourneyStatusDTO(tourney.getStatus());
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

    public Integer getCurrentTour() {
        return currentTour;
    }

    public void setCurrentTour(Integer currentTour) {
        this.currentTour = currentTour;
    }

    public Integer getNextTour() {
        return nextTour;
    }

    public void setNextTour(Integer nextTour) {
        this.nextTour = nextTour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourneyDTO that = (TourneyDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourneyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
