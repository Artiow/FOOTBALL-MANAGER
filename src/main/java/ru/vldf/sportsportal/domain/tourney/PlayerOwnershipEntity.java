package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.domain.common.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "player_ownership", schema = "tourney", catalog = "sportsportal")
public class PlayerOwnershipEntity {
    private Integer id;

    private UserEntity user;
    private PlayerEntity player;

    public PlayerOwnershipEntity() {

    }

    public PlayerOwnershipEntity(UserEntity user, PlayerEntity player) {
        this.user = user;
        this.player = player;
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

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(
            name = "player_id",
            referencedColumnName = "id"
    )
    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }


//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerOwnershipEntity that = (PlayerOwnershipEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerOwnershipEntity{" +
                "id=" + id +
                '}';
    }
}
