package com.scopic.javachallenge.dto;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.validators.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
  @NotNull
  @EnumValidator(
      enumClazz = PlayerPosition.class
  )
  private String position;

  @NotNull
  @EnumValidator(
      enumClazz = Skill.class
  )
  private String mainSkill;

  @NotNull
  @Min(1)
  private Integer numberOfPlayers;
}
