package com.scopic.javachallenge.models;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import lombok.Data;

@Data
public class TeamSelection {
  private PlayerPosition position;

  private Skill mainSkill;

  private int numberOfPlayers;
}
