package com.scopic.javachallenge;

import com.scopic.javachallenge.enums.PlayerPosition;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class PlayerDto {
  private Long id;

  @NotBlank
  private String name;

//  @NotNull
  private PlayerPosition position;

  @Valid
  private Set<PlayerSkillDto> playerSkills;
}
