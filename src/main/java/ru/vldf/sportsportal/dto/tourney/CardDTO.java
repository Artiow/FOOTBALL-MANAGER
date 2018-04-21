package ru.vldf.sportsportal.dto.tourney;

import ru.vldf.sportsportal.domain.tourney.CardEntity;

public class CardDTO {
    private Integer id;
    private String code;
    private String description;

    public CardDTO() {

    }

    public CardDTO(CardEntity card) {
        id = card.getId();
        code = card.getCode();
        description = card.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDTO cardDTO = (CardDTO) o;

        return id != null ? id.equals(cardDTO.id) : cardDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
