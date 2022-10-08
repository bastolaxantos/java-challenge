package com.scopic.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.validators.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
public class PlayerDto {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @EnumValidator(
        enumClazz = PlayerPosition.class
    )
    private String position;

    @Valid
    private Set<PlayerSkillDto> playerSkills;

    @JsonProperty("position")
    public String getPositionLowerCase() {
        return position.toLowerCase();
    }

    public String getPosition() {
        return position.toUpperCase();
    }
}
