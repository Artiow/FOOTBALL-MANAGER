package ru.vldf.sportsportal.model.lease;

import ru.vldf.sportsportal.model.SportEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Playground", schema = "sportsportal")
public class PlaygroundEntity {
    private Integer id;
    private String name;
    private String address;

    private Collection<SportEntity> sports;

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

    @Basic
    @Column(name = "Address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    ==================================================================================
//    === MANY-TO-MANY REFERENCES

    @ManyToMany
    @JoinTable(
            name = "PlaygroundSpecialization",
            joinColumns = @JoinColumn(name = "Playground_ID"),
            inverseJoinColumns = @JoinColumn(name = "Sport_ID")
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}