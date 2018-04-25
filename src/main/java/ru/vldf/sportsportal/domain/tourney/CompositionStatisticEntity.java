package ru.vldf.sportsportal.domain.tourney;

import ru.vldf.sportsportal.dto.tourney.CompositionStatisticDTO;

import javax.persistence.*;

@Entity
@Table(name = "composition_statistic", schema = "tourney", catalog = "sportsportal")
public class CompositionStatisticEntity {
    private Integer id;
    private Integer score = 0;
    private Integer gameNum = 0;
    private Integer winNum = 0;
    private Integer drawNum = 0;
    private Integer defeatNum = 0;
    private Integer cloggedNum = 0;
    private Integer missedNum = 0;
    private Integer diff = 0;

    private CompositionEntity composition;

    public CompositionStatisticEntity() {

    }

    public CompositionStatisticEntity(CompositionStatisticDTO statistic, CompositionEntity composition) {
        id = statistic.getId();
        score = statistic.getScore();
        gameNum = statistic.getGameNum();
        winNum = statistic.getWinNum();
        drawNum = statistic.getDrawNum();
        defeatNum = statistic.getDefeatNum();
        cloggedNum = statistic.getCloggedNum();
        missedNum = statistic.getMissedNum();
        diff = statistic.getDiff();

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

    @Basic
    @Column(name = "diff", nullable = false)
    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }

//    ==================================================================================
//    === ONE-TO-ONE REFERENCES

    @OneToOne
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
