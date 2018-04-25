package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.TourStatusDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tour_status", schema = "tourney", catalog = "sportsportal")
public class TourStatusEntity {
    private Integer id;
    private String code;
    private String description;

    private Collection<TourEntity> tours;

    public TourStatusEntity() {

    }

    public TourStatusEntity(TourStatusDTO status) {
        id = status.getId();
        code = status.getCode();
        description = status.getDescription();
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
    public Collection<TourEntity> getTours() {
        return tours;
    }

    public void setTours(Collection<TourEntity> tours) {
        this.tours = tours;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourStatusEntity that = (TourStatusEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TourStatusEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
