package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionEntity;
import ru.vldf.sportsportal.domain.tourney.CompositionMembershipEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerEntity;
import ru.vldf.sportsportal.domain.tourney.PlayerStatisticEntity;

public class CompositionMembershipDTO {
    private Integer id;

    private PlayerDTO player;
    private CompositionDTO composition;

    private PlayerStatisticDTO statistic;

    public CompositionMembershipDTO() {

    }

    public CompositionMembershipDTO(CompositionMembershipEntity membership) {
        id = membership.getId();

        PlayerEntity playerEntity = membership.getPlayer();
        if (playerEntity != null) player = new PlayerDTO(playerEntity);
        CompositionEntity compositionEntity = membership.getComposition();
        if (compositionEntity != null) composition = new CompositionDTO(compositionEntity);
    }

    public CompositionMembershipDTO(CompositionMembershipEntity membership, boolean includeStatistic) {
        this(membership);

        if (includeStatistic) {
            PlayerStatisticEntity playerStatisticEntity = membership.getStatistic();
            if (playerStatisticEntity != null) statistic = new PlayerStatisticDTO(playerStatisticEntity);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public CompositionDTO getComposition() {
        return composition;
    }

    public void setComposition(CompositionDTO composition) {
        this.composition = composition;
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

        CompositionMembershipDTO that = (CompositionMembershipDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompositionMembershipDTO{" +
                "id=" + id +
                '}';
    }
}
