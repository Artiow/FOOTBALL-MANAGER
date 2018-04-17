package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "composition_membership", schema = "tourney", catalog = "sportsportal")
public class CompositionMembershipEntity {
    private Integer id;

    private PlayerEntity player;
    private CompositionEntity composition;

    public CompositionMembershipEntity() {

    }

    public CompositionMembershipEntity(PlayerEntity player, CompositionEntity composition) {
        this.player = player;
        this.composition = composition;
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
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "composition_id", referencedColumnName = "id", nullable = false)
    public CompositionEntity getComposition() {
        return composition;
    }

    public void setComposition(CompositionEntity composition) {
        this.composition = composition;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionMembershipEntity that = (CompositionMembershipEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionMembershipEntity{" +
                "id=" + id +
                '}';
    }
}
