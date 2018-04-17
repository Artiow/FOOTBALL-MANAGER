package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.CompositionDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "composition", schema = "tourney", catalog = "sportsportal")
public class CompositionEntity {
    private Integer id;
    private String name;
    private Integer shiftbalance = 3; //default

    private TeamEntity team;
    private TourneyEntity tourney;
    private CompositionStatusEntity status;

    private Collection<CompositionMembershipEntity> memberships;

    public CompositionEntity() {

    }

    public CompositionEntity(CompositionDTO teamComposition, TeamEntity team, TourneyEntity tourney, CompositionStatusEntity status) {
        id = teamComposition.getId();
        name = teamComposition.getName();
        shiftbalance = teamComposition.getShiftBalance();

        this.team = team;
        this.tourney = tourney;
        this.status = status;
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
    public Integer getShiftBalance() {
        return shiftbalance;
    }

    public void setShiftBalance(Integer shiftbalance) {
        this.shiftbalance = shiftbalance;
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
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public CompositionStatusEntity getStatus() {
        return status;
    }

    public void setStatus(CompositionStatusEntity status) {
        this.status = status;
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
