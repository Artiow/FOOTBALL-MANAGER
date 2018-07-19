package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "composition_result", schema = "tourney", catalog = "sportsportal")
public class CompositionResultEntity {
    private Integer id;
    private Integer goal = 0;

    private GameEntity game;
    private CompositionStatisticEntity statistic;

    private Collection<PlayerResultEntity> results;

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
//    === ONE-TO_MANY REFERENCES

    @OneToMany(mappedBy = "result")
    public Collection<PlayerResultEntity> getResults() {
        return results;
    }

    public void setResults(Collection<PlayerResultEntity> results) {
        this.results = results;
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
    @JoinColumn(name = "statistic_id", referencedColumnName = "id", nullable = false)
    public CompositionStatisticEntity getStatistic() {
        return statistic;
    }

    public void setStatistic(CompositionStatisticEntity statistic) {
        this.statistic = statistic;
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
