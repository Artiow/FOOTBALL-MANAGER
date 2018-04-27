package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "player_result", schema = "tourney", catalog = "sportsportal")
public class PlayerResultEntity {
    private Integer id;
    private Integer goal = 0;
    private Integer yellowCard = 0;
    private Integer redCard = 0;
    private Boolean present = true;

    private CompositionResultEntity result;
    private PlayerStatisticEntity statistic;

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
    @Column(name = "present", nullable = false)
    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    //    ==================================================================================
//    === MANY-TO-ONE REFERENCES

    @ManyToOne
    @JoinColumn(name = "result_id", referencedColumnName = "id", nullable = false)
    public CompositionResultEntity getResult() {
        return result;
    }

    public void setResult(CompositionResultEntity result) {
        this.result = result;
    }

    @ManyToOne
    @JoinColumn(name = "statistic_id", referencedColumnName = "id", nullable = false)
    public PlayerStatisticEntity getStatistic() {
        return statistic;
    }

    public void setStatistic(PlayerStatisticEntity playerStatisticByStatisticId) {
        this.statistic = playerStatisticByStatisticId;
    }

//    ==================================================================================
//    === OBJECTS METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerResultEntity that = (PlayerResultEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerResultEntity{" +
                "id=" + id +
                ", goal=" + goal +
                ", yellowCard=" + yellowCard +
                ", redCard=" + redCard +
                '}';
    }
}
