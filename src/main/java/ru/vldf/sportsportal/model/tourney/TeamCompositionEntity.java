package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamCompositionDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TeamComposition", schema = "sportsportal")
public class TeamCompositionEntity {
    private Integer id;
    private String teamName;
    private Integer shiftBalance = 3; //default

    private TeamEntity team;
    private TourneyEntity tourney;
    private TeamCompositionStatusEntity status;

    private Collection<TeamCompositionMembershipEntity> memberships;

    public TeamCompositionEntity() {

    }

    public TeamCompositionEntity(TeamCompositionDTO teamComposition, TeamEntity team, TourneyEntity tourney, TeamCompositionStatusEntity status) {
        id = teamComposition.getId();
        teamName = teamComposition.getTeamName();
        shiftBalance = teamComposition.getShiftBalance();

        this.team = team;
        this.tourney = tourney;
        this.status = status;
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
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "composition")
    public Collection<TeamCompositionMembershipEntity> getMemberships() {
        return memberships;
    }

    public void setMemberships(Collection<TeamCompositionMembershipEntity> memberships) {
        this.memberships = memberships;
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

    @ManyToOne
    @JoinColumn(name = "Status_ID", referencedColumnName = "ID", nullable = false)
    public TeamCompositionStatusEntity getStatus() {
        return status;
    }

    public void setStatus(TeamCompositionStatusEntity status) {
        this.status = status;
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
