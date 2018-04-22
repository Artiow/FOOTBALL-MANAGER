package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "composition_statistic", schema = "tourney", catalog = "sportsportal")
public class CompositionStatisticEntity {
    private Integer id;
    private Integer score;
    private Integer gameNum;
    private Integer winNum;
    private Integer drawNum;
    private Integer defeatNum;
    private Integer cloggedNum;
    private Integer missedNum;

    private CompositionEntity composition;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "game_num", nullable = false)
    public Integer getGameNum() {
        return gameNum;
    }

    public void setGameNum(Integer gameNum) {
        this.gameNum = gameNum;
    }

    @Basic
    @Column(name = "win_num", nullable = false)
    public Integer getWinNum() {
        return winNum;
    }

    public void setWinNum(Integer winNum) {
        this.winNum = winNum;
    }

    @Basic
    @Column(name = "draw_num", nullable = false)
    public Integer getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(Integer drawNum) {
        this.drawNum = drawNum;
    }

    @Basic
    @Column(name = "defeat_num", nullable = false)
    public Integer getDefeatNum() {
        return defeatNum;
    }

    public void setDefeatNum(Integer defeatNum) {
        this.defeatNum = defeatNum;
    }

    @Basic
    @Column(name = "clogged_num", nullable = false)
    public Integer getCloggedNum() {
        return cloggedNum;
    }

    public void setCloggedNum(Integer cloggetNum) {
        this.cloggedNum = cloggetNum;
    }

    @Basic
    @Column(name = "missed_num", nullable = false)
    public Integer getMissedNum() {
        return missedNum;
    }

    public void setMissedNum(Integer missedNum) {
        this.missedNum = missedNum;
    }

//    ==================================================================================
//    === MANY-TO-ONE REFERENCES

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

        CompositionStatisticEntity that = (CompositionStatisticEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionStatisticEntity{" +
                "id=" + id +
                '}';
    }
}
