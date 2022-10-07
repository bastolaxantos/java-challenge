package com.scopic.javachallenge;

import com.scopic.javachallenge.enums.Skill;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlayerSkillDto {
  private Long id;

  @NotNull
  private Skill skill;

  @NotNull
  private Integer value;
}
