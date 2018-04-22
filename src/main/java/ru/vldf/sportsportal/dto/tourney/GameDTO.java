package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.GameEntity;

public class GameDTO {
    private Integer id;
    private String timegrid;

    private CompositionDTO red;
    private CompositionDTO blue;
    private TourDTO tour;

    public GameDTO() {

    }

    public GameDTO(GameEntity game) {
        id = game.getId();
        timegrid = game.getTimegrid();

        if (game.getRed() != null) red = new CompositionDTO(game.getRed());
        if (game.getBlue() != null) blue = new CompositionDTO(game.getBlue());
        if (game.getTour() != null) tour = new TourDTO(game.getTour());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimegrid() {
        return timegrid;
    }

    public void setTimegrid(String timegrid) {
        this.timegrid = timegrid;
    }

    public CompositionDTO getRed() {
        return red;
    }

    public void setRed(CompositionDTO red) {
        this.red = red;
    }

    public CompositionDTO getBlue() {
        return blue;
    }

    public void setBlue(CompositionDTO blue) {
        this.blue = blue;
    }

    public TourDTO getTour() {
        return tour;
    }

    public void setTour(TourDTO tour) {
        this.tour = tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameDTO gameDTO = (GameDTO) o;

        return id != null ? id.equals(gameDTO.id) : gameDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "id=" + id +
                ", timegrid='" + timegrid + '\'' +
                '}';
    }
}
