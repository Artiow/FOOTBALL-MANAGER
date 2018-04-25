package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TourEntity;
import ru.vldf.sportsportal.domain.tourney.TourStatusEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;

public class TourDTO {
    private Integer id;
    private Integer num;

    private TourneyDTO tourney;
    private TourStatusDTO status;

    public TourDTO() {

    }

    public TourDTO(TourEntity tour) {
        id = tour.getId();
        num = tour.getNum();

        TourneyEntity tourneyEntity = tour.getTourney();
        if (tourneyEntity != null) tourney = new TourneyDTO(tourneyEntity);
        TourStatusEntity tourStatusEntity = tour.getStatus();
        if (tourStatusEntity != null) status = new TourStatusDTO(tourStatusEntity);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public TourneyDTO getTourney() {
        return tourney;
    }

    public void setTourney(TourneyDTO tourney) {
        this.tourney = tourney;
    }

    public TourStatusDTO getStatus() {
        return status;
    }

    public void setStatus(TourStatusDTO status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourDTO tourDTO = (TourDTO) o;

        return id != null ? id.equals(tourDTO.id) : tourDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourDTO{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
