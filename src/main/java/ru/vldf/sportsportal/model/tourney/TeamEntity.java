package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.model.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "Team", schema = "sportsportal")
public class TeamEntity {
    private Integer id;
    private String name;

    private UserEntity captain;
    private TeamStatusEntity status;

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