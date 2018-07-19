package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionStatisticEntity;

public class CompositionStatisticDTO {
    private Integer id;
    private Integer score = 0;
    private Integer gameNum = 0;
    private Integer winNum = 0;
    private Integer drawNum = 0;
    private Integer defeatNum = 0;
    private Integer cloggedNum = 0;
    private Integer missedNum = 0;
    private Integer diff = 0;

    private CompositionDTO composition;

    public CompositionStatisticDTO() {

    }

    public CompositionStatisticDTO(CompositionStatisticEntity statistic) {
        id = statistic.getId();
        score = statistic.getScore();
        gameNum = statistic.getGameNum();
        winNum = statistic.getWinNum();
        drawNum = statistic.getDrawNum();
        defeatNum = statistic.getDefeatNum();
        cloggedNum = statistic.getCloggedNum();
        missedNum = statistic.getMissedNum();
        diff = statistic.getDiff();
    }

    public CompositionStatisticDTO(CompositionStatisticEntity statistic, boolean includeComposition) {
        this(statistic);

        if (includeComposition) {
            CompositionEntity compositionEntity = statistic.getComposition();
            if (compositionEntity != null) composition = new CompositionDTO(compositionEntity);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getGameNum() {
        return gameNum;
    }

    public void setGameNum(Integer gameNum) {
        this.gameNum = gameNum;
    }

    public Integer getWinNum() {
        return winNum;
    }

    public void setWinNum(Integer winNum) {
        this.winNum = winNum;
    }

    public Integer getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(Integer drawNum) {
        this.drawNum = drawNum;
    }

    public Integer getDefeatNum() {
        return defeatNum;
    }

    public void setDefeatNum(Integer defeatNum) {
        this.defeatNum = defeatNum;
    }

    public Integer getCloggedNum() {
        return cloggedNum;
    }

    public void setCloggedNum(Integer cloggedNum) {
        this.cloggedNum = cloggedNum;
    }

    public Integer getMissedNum() {
        return missedNum;
    }

    public void setMissedNum(Integer missedNum) {
        this.missedNum = missedNum;
    }

    public CompositionDTO getComposition() {
        return composition;
    }

    public void setComposition(CompositionDTO composition) {
        this.composition = composition;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionStatisticDTO that = (CompositionStatisticDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionStatisticDTO{" +
                "id=" + id +
                '}';
    }
}
