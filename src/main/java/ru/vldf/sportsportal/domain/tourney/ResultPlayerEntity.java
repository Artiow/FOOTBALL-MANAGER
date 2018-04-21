package ru.vldf.sportsportal.domain.tourney;

import javax.persistence.*;

@Entity
@Table(name = "result_player", schema = "tourney", catalog = "sportsportal")
public class ResultPlayerEntity {
    private Integer id;
    private Boolean presence;
    private Integer goal;

    private CardEntity card;
    private PlayerEntity player;
    private ResultTeamEntity result;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "presence", nullable = false)
    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }

    @Basic
    @Column(name = "goal", nullable = false)
    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultPlayerEntity that = (ResultPlayerEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (presence != null ? !presence.equals(that.presence) : that.presence != null) return false;
        if (goal != null ? !goal.equals(that.goal) : that.goal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (presence != null ? presence.hashCode() : 0);
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "team_result_id", referencedColumnName = "id", nullable = false)
    public ResultTeamEntity getResult() {
        return result;
    }

    public void setResult(ResultTeamEntity result) {
        this.result = result;
    }

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
