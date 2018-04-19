package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.PlayerStatusDTO;

import javax.persistence.*;

@Entity
@Table(name = "player_status", schema = "tourney", catalog = "sportsportal")
public class PlayerStatusEntity {
    private Integer id;
    private String code;
    private String description;

    public PlayerStatusEntity() {

    }

    public PlayerStatusEntity(PlayerStatusDTO status) {
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
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerStatusEntity that = (PlayerStatusEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerStatusEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
