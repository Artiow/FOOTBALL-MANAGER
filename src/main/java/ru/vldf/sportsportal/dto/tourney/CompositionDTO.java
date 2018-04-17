package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;

public class CompositionDTO {
    private Integer id;
    private String name;
    private Integer shiftbalance = 3; //default

    private TeamDTO team;
    private TourneyDTO tourney;
    private CompositionStatusDTO status;

    public CompositionDTO() {

    }

    public CompositionDTO(CompositionEntity teamComposition) {
        id = teamComposition.getId();
        name = teamComposition.getName();
        shiftbalance = teamComposition.getShiftBalance();

        if (teamComposition.getTeam() != null) team = new TeamDTO(teamComposition.getTeam());
        if (teamComposition.getTourney() != null) tourney = new TourneyDTO(teamComposition.getTourney());
        if (teamComposition.getStatus() != null) status = new CompositionStatusDTO(teamComposition.getStatus());
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
