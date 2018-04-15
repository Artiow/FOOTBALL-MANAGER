package ru.vldf.sportsportal.model.lease;

import ru.vldf.sportsportal.dto.lease.SportDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Sport", schema = "public", catalog = "sportsportal")
public class SportEntity {
    private Integer id;
    private String name;

    private Collection<PlaygroundEntity> playgrounds;

    public SportEntity() {

    }

    public SportEntity(SportDTO sport) {
        id = sport.getId();
        name = sport.getName();
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
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "PlaygroundSpec",
            joinColumns = @JoinColumn(name = "Sport_ID"),
            inverseJoinColumns = @JoinColumn(name = "Playground_ID")
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