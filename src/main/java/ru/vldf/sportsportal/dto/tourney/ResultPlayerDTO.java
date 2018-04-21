package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.ResultPlayerEntity;

public class ResultPlayerDTO {
    private Integer id;
    private Boolean presence;
    private Integer goal;

    private CardDTO card;
    private PlayerDTO player;
    private ResultTeamDTO result;

    public ResultPlayerDTO() {

    }

    public ResultPlayerDTO(ResultPlayerEntity result) {
        id = result.getId();
        presence = result.getPresence();
        goal = result.getGoal();

        if (result.getCard() != null) card = new CardDTO(result.getCard());
        if (result.getPlayer() != null) player = new PlayerDTO(result.getPlayer());
        if (result.getResult() != null) this.result = new ResultTeamDTO(result.getResult());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public CardDTO getCard() {
        return card;
    }

    public void setCard(CardDTO card) {
        this.card = card;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public ResultTeamDTO getResult() {
        return result;
    }

    public void setResult(ResultTeamDTO result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultPlayerDTO that = (ResultPlayerDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
