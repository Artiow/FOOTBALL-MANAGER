package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "player_statistic", schema = "tourney", catalog = "sportsportal")
public class PlayerStatisticEntity {
    private Integer id;
    private Integer goal = 0;
    private Integer yellowCard = 0;
    private Integer redCard = 0;
    private Integer gameNum = 0;

    private CompositionMembershipEntity membership;

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

    @Basic
    @Column(name = "yellow_card", nullable = false)
    public Integer getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Integer yellowCard) {
        this.yellowCard = yellowCard;
    }

    @Basic
    @Column(name = "red_card", nullable = false)
    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
    }

    @Basic
    @Column(name = "game_num", nullable = false)
    public Integer getGameNum() {
        return gameNum;
    }

    public void setGameNum(Integer gameNum) {
        this.gameNum = gameNum;
    }

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne
    @JoinColumn(name = "membership_id", referencedColumnName = "id", nullable = false)
    public CompositionMembershipEntity getMembership() {
        return membership;
    }

    public void setMembership(CompositionMembershipEntity membership) {
        this.membership = membership;
    }

//    ==================================================================================
//    === ONE-TO-MANY REFERENCES

    @OneToMany(mappedBy = "statistic")
    public Collection<PlayerResultEntity> getResults() {
        return results;
    }

    public void setResults(Collection<PlayerResultEntity> playerResultsById) {
        this.results = playerResultsById;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerStatisticEntity that = (PlayerStatisticEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerStatisticEntity{" +
                "id=" + id +
                ", goal=" + goal +
                ", yellowCard=" + yellowCard +
                ", redCard=" + redCard +
                '}';
    }
}
