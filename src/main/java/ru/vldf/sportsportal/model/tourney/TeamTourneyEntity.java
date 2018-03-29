package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamTourneyDTO;
import ru.vldf.sportsportal.model.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "TeamTourney", schema = "sportsportal")
public class TeamTourneyEntity {
    private Integer id;
    private String name;

    private UserEntity captain;
    private TeamTourneyStatusEntity status;

    public TeamTourneyEntity() {

    }

    public TeamTourneyEntity(TeamTourneyDTO teamTourneyDTO, UserEntity captain, TeamTourneyStatusEntity status) {
        id = teamTourneyDTO.getId();
        name = teamTourneyDTO.getName();

        this.captain = captain;
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
    @Column(name = "Name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(
            name = "Captain_ID",
            referencedColumnName = "ID",
            nullable = false
    )
    public UserEntity getCaptain() {
        return captain;
    }

    public void setCaptain(UserEntity captain) {
        this.captain = captain;
    }

    @ManyToOne
    @JoinColumn(
            name = "Status_ID",
            referencedColumnName = "ID",
            nullable = false
    )
    public TeamTourneyStatusEntity getStatus() {
        return status;
    }

    public void setStatus(TeamTourneyStatusEntity status) {
        this.status = status;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamTourneyEntity that = (TeamTourneyEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}