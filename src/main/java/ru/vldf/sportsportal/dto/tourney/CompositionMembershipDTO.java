package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CompositionMembershipEntity;

public class CompositionMembershipDTO {
    private Integer id;

    private PlayerDTO player;
    private CompositionDTO composition;

    public CompositionMembershipDTO() {

    }

    public CompositionMembershipDTO(CompositionMembershipEntity membership) {
        id = membership.getId();

        if (membership.getPlayer() != null) player = new PlayerDTO(membership.getPlayer());
        if (membership.getComposition() != null) composition = new CompositionDTO(membership.getComposition());
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
