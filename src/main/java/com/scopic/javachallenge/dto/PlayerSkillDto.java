package com.scopic.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.validators.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PlayerSkillDto {
    private Long id;

    @NotNull
    @EnumValidator(
        enumClazz = Skill.class
    )
    private String skill;

    @NotNull
    private Integer value;

    private Long playerId;

    @JsonProperty("skill")
    public String getSkillLowerCase() {
        return skill.toLowerCase();
    }

    public String getSkill() {
        return skill.toUpperCase();
    }
}
