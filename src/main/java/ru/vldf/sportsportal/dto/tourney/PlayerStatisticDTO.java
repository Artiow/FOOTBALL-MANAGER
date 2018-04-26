package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionMembershipEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerStatisticEntity;

public class PlayerStatisticDTO {
    private Integer id;
    private Integer goal = 0;
    private Integer yellowCard = 0;
    private Integer redCard = 0;

    private CompositionMembershipDTO membership;

    public PlayerStatisticDTO() {

    }

    public PlayerStatisticDTO(PlayerStatisticEntity statistic) {
        id = statistic.getId();
        goal = statistic.getGoal();
        yellowCard = statistic.getYellowCard();
        redCard = statistic.getRedCard();
    }

    public PlayerStatisticDTO(PlayerStatisticEntity statistic, boolean includeMembership) {
        this(statistic);

        if (includeMembership) {
            CompositionMembershipEntity compositionMembershipEntity = statistic.getMembership();
            if (compositionMembershipEntity != null) membership = new CompositionMembershipDTO(compositionMembershipEntity);
        }
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

    public CompositionMembershipDTO getMembership() {
        return membership;
    }

    public void setMembership(CompositionMembershipDTO membership) {
        this.membership = membership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerStatisticDTO that = (PlayerStatisticDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerStatisticDTO{" +
                "id=" + id +
                ", goal=" + goal +
                ", yellowCard=" + yellowCard +
                ", redCard=" + redCard +
                '}';
    }
}
