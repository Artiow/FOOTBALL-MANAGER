package ru.vldf.sportsportal.model.tourney;

import javax.persistence.*;

@Entity
@Table(name = "teamcompositionmembership", schema = "public", catalog = "sportsportal")
public class TeamCompositionMembershipEntity {
    private Integer id;

    private TeamPlayerEntity player;
    private TeamCompositionEntity composition;

    public TeamCompositionMembershipEntity() {

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


//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "TeamPlayer_ID", referencedColumnName = "ID", nullable = false)
    public TeamPlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(TeamPlayerEntity player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "TeamComposition_ID", referencedColumnName = "ID", nullable = false)
    public TeamCompositionEntity getComposition() {
        return composition;
    }

    public void setComposition(TeamCompositionEntity composition) {
        this.composition = composition;
    }

    //    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamCompositionMembershipEntity that = (TeamCompositionMembershipEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
