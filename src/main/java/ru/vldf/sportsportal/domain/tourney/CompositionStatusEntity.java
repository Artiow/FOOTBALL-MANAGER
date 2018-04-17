package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.CompositionStatusDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "composition_status", schema = "tourney", catalog = "sportsportal")
public class CompositionStatusEntity {
    private Integer id;
    private String code;
    private String description;

    private Collection<CompositionEntity> compositions;

    public CompositionStatusEntity() {

    }

    public CompositionStatusEntity(CompositionStatusDTO statusDTO) {
        id = statusDTO.getId();
        code = statusDTO.getCode();
        description = statusDTO.getDescription();
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
    @Column(name = "code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 90)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "status")
    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> compositions) {
        this.compositions = compositions;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionStatusEntity that = (CompositionStatusEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionStatusEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
