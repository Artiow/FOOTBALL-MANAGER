package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionStatisticEntity;
import ru.vldf.sportsportal.domain.tourney.TeamEntity;
import ru.vldf.sportsportal.domain.tourney.TourneyEntity;
import ru.vldf.sportsportal.dto.lease.PlaygroundDTO;

public class CompositionDTO {
    private Integer id;
    private String name;
    private Integer shiftbalance = 3;       //default
    private String timegrid = "CCCCCCCCCC"; //default

    private TeamDTO team;
    private TourneyDTO tourney;
    private PlaygroundDTO playground;

    private CompositionStatisticDTO statistic;

    public CompositionDTO() {

    }

    public CompositionDTO(CompositionEntity composition) {
        id = composition.getId();
        name = composition.getName();
        shiftbalance = composition.getShiftbalance();
        timegrid = composition.getTimegrid();

        TeamEntity teamEntity = composition.getTeam();
        if (teamEntity != null) team = new TeamDTO(teamEntity);
        TourneyEntity tourneyEntity = composition.getTourney();
        if (tourneyEntity != null) tourney = new TourneyDTO(tourneyEntity);
        PlaygroundEntity playgroundEntity = composition.getPlayground();
        if (playgroundEntity != null) playground = new PlaygroundDTO(playgroundEntity);
    }

    public CompositionDTO(CompositionEntity composition, boolean includeStatistic) {
        this(composition);

        if (includeStatistic) {
            CompositionStatisticEntity statisticEntity = composition.getStatistic();
            if (statisticEntity != null) statistic = new CompositionStatisticDTO(statisticEntity);
        }
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

    public Integer getShiftbalance() {
        return shiftbalance;
    }

    public void setShiftbalance(Integer shiftbalance) {
        this.shiftbalance = shiftbalance;
    }

    public String getTimegrid() {
        return timegrid;
    }

    public void setTimegrid(String timegrid) {
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

    public PlaygroundDTO getPlayground() {
        return playground;
    }

    public void setPlayground(PlaygroundDTO playground) {
        this.playground = playground;
    }

    public CompositionStatisticDTO getStatistic() {
        return statistic;
    }

    public void setStatistic(CompositionStatisticDTO statistic) {
        this.statistic = statistic;
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
