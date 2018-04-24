package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "composition_result", schema = "tourney", catalog = "sportsportal")
public class CompositionResultEntity {
    private Integer id;
    private Integer goal = 0;

    private GameEntity game;
    private CompositionEntity composition;

    public CompositionResultEntity() {

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
    @Column(name = "goal", nullable = false)
    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
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
//    === OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionResultEntity that = (CompositionResultEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResultTeamEntity{" +
                "id=" + id +
                '}';
    }
}
