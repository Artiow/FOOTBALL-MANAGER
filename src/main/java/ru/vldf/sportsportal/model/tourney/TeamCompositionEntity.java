package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamCompositionDTO;
import ru.vldf.sportsportal.model.user.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TeamComposition", schema = "sportsportal")
public class TeamCompositionEntity {
    private Integer id;
    private String teamName;
    private Integer shiftBalance;

    private TeamEntity team;
    private TourneyEntity tourney;

    private Collection<UserEntity> users;
    private Collection<TeamPlayerEntity> players;

    public TeamCompositionEntity() {

    }

    public TeamCompositionEntity(TeamCompositionDTO teamComposition, TeamEntity team, TourneyEntity tourney) {
        id = teamComposition.getId();
        teamName = teamComposition.getTeamName();
        shiftBalance = teamComposition.getShiftBalance();

        this.team = team;
        this.tourney = tourney;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TeamName", nullable = false, length = 45)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "ShiftBalance", nullable = false)
    public Integer getShiftBalance() {
        return shiftBalance;
    }

    public void setShiftBalance(Integer shiftBalance) {
        this.shiftBalance = shiftBalance;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "Team_ID", referencedColumnName = "ID", nullable = false)
    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    @ManyToOne
    @JoinColumn(name = "Tourney_ID", referencedColumnName = "ID", nullable = false)
    public TourneyEntity getTourney() {
        return tourney;
    }

    public void setTourney(TourneyEntity tourney) {
        this.tourney = tourney;
    }

//    ==================================================================================
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "TeamMembershipForUser",
            joinColumns = @JoinColumn(name = "TeamComposition_ID"),
            inverseJoinColumns = @JoinColumn(name = "TeamUser_ID")
    )
    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

    @ManyToMany
    @JoinTable(
            name = "TeamMembershipForPlayer",
            joinColumns = @JoinColumn(name = "TeamComposition_ID"),
            inverseJoinColumns = @JoinColumn(name = "TeamPlayer_ID")
    )
    public Collection<TeamPlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<TeamPlayerEntity> players) {
        this.players = players;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamCompositionEntity that = (TeamCompositionEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
