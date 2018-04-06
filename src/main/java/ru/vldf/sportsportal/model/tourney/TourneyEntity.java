package ru.vldf.sportsportal.model.tourney;

import ru.vldf.sportsportal.dto.tourney.TourneyDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Tourney", schema = "sportsportal")
public class TourneyEntity {
    private Integer id;
    private String name;

    private Collection<TeamCompositionEntity> teamCompositionsById;

    public TourneyEntity() {

    }

    public TourneyEntity(TourneyDTO tourneyDTO) {
        id = tourneyDTO.getId();
        name= tourneyDTO.getName();
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

    @OneToMany(mappedBy = "tourneyByTourneyId")
    public Collection<TeamCompositionEntity> getTeamCompositionsById() {
        return teamCompositionsById;
    }

    public void setTeamCompositionsById(Collection<TeamCompositionEntity> teamCompositionsById) {
        this.teamCompositionsById = teamCompositionsById;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourneyEntity that = (TourneyEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
