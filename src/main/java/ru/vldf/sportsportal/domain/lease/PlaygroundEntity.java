package ru.vldf.sportsportal.domain.lease;

import ru.vldf.sportsportal.domain.common.SportEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.dto.lease.PlaygroundDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "playground", schema = "lease", catalog = "sportsportal")
public class PlaygroundEntity {
    private Integer id;
    private String name;
    private String address;
    private String description;

    private Collection<SportEntity> sports;
    private Collection<CompositionEntity> compositions;

    public PlaygroundEntity() {

    }

    public PlaygroundEntity(PlaygroundDTO playground) {
        id = playground.getId();
        name = playground.getName();
        address = playground.getAddress();
        description = playground.getDescription();
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

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @OneToMany(mappedBy = "playground")
    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> compositions) {
        this.compositions = compositions;
    }


//    ==================================================================================
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "playground_sport",
            joinColumns = @JoinColumn(name = "playground_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    public Collection<SportEntity> getSports() {
        return sports;
    }

    public void setSports(Collection<SportEntity> sports) {
        this.sports = sports;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaygroundEntity that = (PlaygroundEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlaygroundEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}