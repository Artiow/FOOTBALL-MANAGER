package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TeamCompositionStatusDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "teamcompositionstatus", schema = "sportsportal")
public class TeamCompositionStatusEntity {
    private Integer id;
    private String code;
    private String description;

    private Collection<TeamCompositionEntity> compositions;

    public TeamCompositionStatusEntity() {

    }

    public TeamCompositionStatusEntity(TeamCompositionStatusDTO statusDTO) {
        id = statusDTO.getId();
        code = statusDTO.getCode();
        description = statusDTO.getDescription();
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
    @Column(name = "Code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 90)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "status")
    public Collection<TeamCompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<TeamCompositionEntity> compositions) {
        this.compositions = compositions;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamCompositionStatusEntity that = (TeamCompositionStatusEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
