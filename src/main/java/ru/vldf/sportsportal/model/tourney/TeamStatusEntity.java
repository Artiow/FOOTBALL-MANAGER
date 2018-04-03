package ru.vldf.sportsportal.model.tourney;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "TeamStatus", schema = "sportsportal")
public class TeamStatusEntity {
    private Integer id;
    private String code;
    private String description;

    private Collection<TeamEntity> teams;

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
    @Column(name = "Code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "Description", length = 90)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "status")
    public Collection<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(Collection<TeamEntity> teams) {
        this.teams = teams;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatusEntity that = (TeamStatusEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }
}
