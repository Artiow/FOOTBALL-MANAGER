package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.GameEntity;
import ru.vldf.sportsportal.domain.tourney.TimegridEntity;
import ru.vldf.sportsportal.domain.tourney.TourEntity;

public class GameDTO {
    private Integer id;
    private String timegrid;

    private TimegridDTO time;
    private CompositionDTO red;
    private CompositionDTO blue;
    private TourDTO tour;

    public GameDTO() {

    }

    public GameDTO(GameEntity game) {
        id = game.getId();
        timegrid = game.getTimegrid();

        TimegridEntity timegridEntity = game.getTime();
        if (timegridEntity != null) time = new TimegridDTO(timegridEntity);
        CompositionEntity redEntity = game.getRed();
        if (redEntity != null) red = new CompositionDTO(redEntity);
        CompositionEntity blueEntity = game.getBlue();
        if (blueEntity != null) blue = new CompositionDTO(blueEntity);
        TourEntity tourEntity = game.getTour();
        if (tourEntity != null) tour = new TourDTO(tourEntity);
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

    public TimegridDTO getTime() {
        return time;
    }

    public void setTime(TimegridDTO time) {
        this.time = time;
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
