package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.TourEntity;

public class TourDTO {
    private Integer id;
    private Integer num;

    private TourneyDTO tourney;

    public TourDTO() {

    }

    public TourDTO(TourEntity tour) {
        id = tour.getId();
        num = tour.getNum();

        if (tour.getTourney() != null) tourney = new TourneyDTO(tour.getTourney());
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
                '}';
    }
}
