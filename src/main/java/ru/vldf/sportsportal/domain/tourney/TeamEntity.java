package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.domain.common.UserEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "team", schema = "tourney", catalog = "sportsportal")
public class TeamEntity {
    private Integer id;
    private String name;

    private TeamStatusEntity status;
    private UserEntity captain;

    private Collection<CompositionEntity> compositions;

    public TeamEntity() {

    }

    public TeamEntity(TeamDTO teamDTO, UserEntity captain, TeamStatusEntity status) {
        id = teamDTO.getId();
        name = teamDTO.getName();

        this.captain = captain;
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

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "team")
    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> teamCompositionsById) {
        this.compositions = teamCompositionsById;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(
            name = "captain_id",
            referencedColumnName = "id",
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
            name = "status_id",
            referencedColumnName = "id",
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

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}