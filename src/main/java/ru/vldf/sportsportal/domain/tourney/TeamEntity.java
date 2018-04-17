package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.domain.user.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Team", schema = "public", catalog = "sportsportal")
public class TeamEntity {
    private Integer id;
    private String name;

    private UserEntity captain;
    private TeamStatusEntity status;

    private Collection<TeamCompositionEntity> teamCompositions;

    public TeamEntity() {

    }

    public TeamEntity(TeamDTO teamDTO, UserEntity captain, TeamStatusEntity status) {
        id = teamDTO.getId();
        name = teamDTO.getName();

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
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "team")
    public Collection<TeamCompositionEntity> getTeamCompositions() {
        return teamCompositions;
    }

    public void setTeamCompositions(Collection<TeamCompositionEntity> teamCompositionsById) {
        this.teamCompositions = teamCompositionsById;
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
    public TeamStatusEntity getStatus() {
        return status;
    }

    public void setStatus(TeamStatusEntity status) {
        this.status = status;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEntity that = (TeamEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}