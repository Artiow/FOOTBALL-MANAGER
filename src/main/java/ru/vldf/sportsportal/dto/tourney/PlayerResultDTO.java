package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionResultEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerResultEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerStatisticEntity;

public class PlayerResultDTO {
    private Integer id;
    private Integer goal = 0;
    private Integer yellowCard = 0;
    private Integer redCard = 0;

    private CompositionResultDTO result;
    private PlayerStatisticDTO statistic;

    public PlayerResultDTO() {

    }

    public PlayerResultDTO(PlayerResultEntity result) {
        id = result.getId();
        goal = result.getGoal();
        yellowCard = result.getYellowCard();
        redCard = result.getRedCard();

        CompositionResultEntity compositionResultEntity = result.getResult();
        if (compositionResultEntity != null) this.result = new CompositionResultDTO(compositionResultEntity);
        PlayerStatisticEntity playerStatisticEntity = result.getStatistic();
        if (playerStatisticEntity != null) this.statistic = new PlayerStatisticDTO(playerStatisticEntity, true);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Integer yellowCard) {
        this.yellowCard = yellowCard;
    }

    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
    }

    public CompositionResultDTO getResult() {
        return result;
    }

    public void setResult(CompositionResultDTO result) {
        this.result = result;
    }

    public PlayerStatisticDTO getStatistic() {
        return statistic;
    }

    public void setStatistic(PlayerStatisticDTO statistic) {
        this.statistic = statistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerResultDTO that = (PlayerResultDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerResultDTO{" +
                "id=" + id +
                ", goal=" + goal +
                ", yellowCard=" + yellowCard +
                ", redCard=" + redCard +
                '}';
    }
}
