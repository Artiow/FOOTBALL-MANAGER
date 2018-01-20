package ru.vldf.sportsportal.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Playground", schema = "sportsportal")
public class PlaygroundEntity {
    private Integer id;
    private String name;
    private String address;

    private Collection<PlaygroundSpecializationEntity> specializations;

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
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "playground")
    public Collection<PlaygroundSpecializationEntity> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Collection<PlaygroundSpecializationEntity> specializations) {
        this.specializations = specializations;
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
}