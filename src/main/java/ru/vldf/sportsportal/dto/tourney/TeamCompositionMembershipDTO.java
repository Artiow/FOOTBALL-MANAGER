package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.model.tourney.TeamCompositionMembershipEntity;

public class TeamCompositionMembershipDTO {
    private Integer id;

    private TeamPlayerDTO player;
    private TeamCompositionDTO composition;

    public TeamCompositionMembershipDTO() {

    }

    public TeamCompositionMembershipDTO(TeamCompositionMembershipEntity membership) {
        id = membership.getId();

        if (membership.getPlayer() != null) player = new TeamPlayerDTO(membership.getPlayer());
        if (membership.getComposition() != null) composition = new TeamCompositionDTO(membership.getComposition());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamPlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(TeamPlayerDTO player) {
        this.player = player;
    }

    public TeamCompositionDTO getComposition() {
        return composition;
    }

    public void setComposition(TeamCompositionDTO composition) {
        this.composition = composition;
    }
}
