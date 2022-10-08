package com.scopic.javachallenge.services;

import com.scopic.javachallenge.dto.TeamDto;
import com.scopic.javachallenge.models.Player;

import java.util.List;

public interface PlayerService {
    Player save(Player player);

    Player getById(Long id);

    List<Player> getAll();

    Player update(Player player, Long id);

    void delete(Long id);

    List<Player> selectTeam(List<TeamDto> teamList);
}
