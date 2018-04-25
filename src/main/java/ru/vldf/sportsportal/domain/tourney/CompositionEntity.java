package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.dto.tourney.CompositionDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "composition", schema = "tourney", catalog = "sportsportal")
public class CompositionEntity {
    private Integer id;
    private String name;
    private Integer shiftbalance = 3;       //default
    private String timegrid = "CCCCCCCCCC"; //default

    private TeamEntity team;
    private TourneyEntity tourney;
    private PlaygroundEntity playground;

    private CompositionStatisticEntity statistic;
    private Collection<CompositionMembershipEntity> memberships;
    private Collection<CompositionResultEntity> results;
    private Collection<GameEntity> redGames;
    private Collection<GameEntity> blueGames;

    public CompositionEntity() {

    }

    public CompositionEntity(CompositionDTO composition, TeamEntity team, TourneyEntity tourney) {
        id = composition.getId();
        name = composition.getName();
        shiftbalance = composition.getShiftBalance();
        timegrid = composition.getTimegrid();

        this.team = team;
        this.tourney = tourney;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "shiftbalance", nullable = false)
    public Integer getShiftbalance() {
        return shiftbalance;
    }

    public void setShiftbalance(Integer shiftbalance) {
        this.shiftbalance = shiftbalance;
    }

    @Basic
    @Column(name = "timegrid", nullable = false, length = 10)
    public String getTimegrid() {
        return timegrid;
    }

    public void setTimegrid(String timegrid) {
        this.timegrid = timegrid;
    }

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne(mappedBy = "composition")
    public CompositionStatisticEntity getStatistic() {
        return statistic;
    }

    public void setStatistic(CompositionStatisticEntity statistic) {
        this.statistic = statistic;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "composition")
    public Collection<CompositionMembershipEntity> getMemberships() {
        return memberships;
    }

    public void setMemberships(Collection<CompositionMembershipEntity> memberships) {
        this.memberships = memberships;
    }

    @OneToMany(mappedBy = "composition")
    public Collection<CompositionResultEntity> getResults() {
        return results;
    }

    public void setResults(Collection<CompositionResultEntity> results) {
        this.results = results;
    }

    @OneToMany(mappedBy = "red")
    public Collection<GameEntity> getRedGames() {
        return redGames;
    }

    public void setRedGames(Collection<GameEntity> redGames) {
        this.redGames = redGames;
    }

    @OneToMany(mappedBy = "blue")
    public Collection<GameEntity> getBlueGames() {
        return blueGames;
    }

    public void setBlueGames(Collection<GameEntity> blueGames) {
        this.blueGames = blueGames;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    @ManyToOne
    @JoinColumn(name = "tourney_id", referencedColumnName = "id", nullable = false)
    public TourneyEntity getTourney() {
        return tourney;
    }

    public void setTourney(TourneyEntity tourney) {
        this.tourney = tourney;
    }

    @ManyToOne
    @JoinColumn(name = "playground_id", referencedColumnName = "id", nullable = true)
    public PlaygroundEntity getPlayground() {
        return playground;
    }

    public void setPlayground(PlaygroundEntity playground) {
        this.playground = playground;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionEntity that = (CompositionEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
