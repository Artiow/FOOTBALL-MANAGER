package ru.vldf.sportsportal.domain.common;

import ru.vldf.sportsportal.domain.lease.PlaygroundEntity;
import ru.vldf.sportsportal.dto.common.SportDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sport", schema = "common", catalog = "sportsportal")
public class SportEntity {
    private Integer id;
    private String code;
    private String description;

    private Collection<PlaygroundEntity> playgrounds;

    public SportEntity() {

    }

    public SportEntity(SportDTO sport) {
        id = sport.getId();
        code = sport.getCode();
        description = sport.getDescription();
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

    public void setCode(String name) {
        this.code = name;
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
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "playground_sport",
            joinColumns = @JoinColumn(name = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "playground_id")
    )
    public Collection<PlaygroundEntity> getPlaygrounds() {
        return playgrounds;
    }

    public void setPlaygrounds(Collection<PlaygroundEntity> playgrounds) {
        this.playgrounds = playgrounds;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportEntity that = (SportEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SportEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}