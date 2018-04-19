package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;

public class CompositionDTO {
    private Integer id;
    private String name;
    private Integer shiftbalance = 3;       //default
    private String timegrid = "CCCCCCCCCC"; //default

    private TeamDTO team;
    private TourneyDTO tourney;
    private CompositionStatusDTO status;

    public CompositionDTO() {

    }

    public CompositionDTO(CompositionEntity composition) {
        id = composition.getId();
        name = composition.getName();
        shiftbalance = composition.getShiftBalance();
        timegrid = composition.getTimeGrid();

        if (composition.getTeam() != null) team = new TeamDTO(composition.getTeam());
        if (composition.getTourney() != null) tourney = new TourneyDTO(composition.getTourney());
        if (composition.getStatus() != null) status = new CompositionStatusDTO(composition.getStatus());
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

    public Integer getShiftBalance() {
        return shiftbalance;
    }

    public void setShiftBalance(Integer shiftbalance) {
        this.shiftbalance = shiftbalance;
    }

    public String getTimeGrid() {
        return timegrid;
    }

    public void setTimeGrid(String timegrid) {
        this.timegrid = timegrid;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public TourneyDTO getTourney() {
        return tourney;
    }

    public void setTourney(TourneyDTO tourney) {
        this.tourney = tourney;
    }

    public CompositionStatusDTO getStatus() {
        return status;
    }

    public void setStatus(CompositionStatusDTO status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionDTO that = (CompositionDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
