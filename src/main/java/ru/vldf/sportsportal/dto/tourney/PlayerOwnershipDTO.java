package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.PlayerOwnershipEntity;
import ru.vldf.sportsportal.dto.common.UserDTO;

public class PlayerOwnershipDTO {
    private Integer id;

    private UserDTO user;
    private PlayerDTO player;

    public PlayerOwnershipDTO() {

    }

    public PlayerOwnershipDTO(PlayerOwnershipEntity playerOwnership) {
        id = playerOwnership.getId();

        if (playerOwnership.getUser() != null) user = new UserDTO(playerOwnership.getUser());
        if (playerOwnership.getPlayer() != null) player = new PlayerDTO(playerOwnership.getPlayer());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerOwnershipDTO that = (PlayerOwnershipDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlayerOwnershipDTO{" +
                "id=" + id +
                '}';
    }
}
