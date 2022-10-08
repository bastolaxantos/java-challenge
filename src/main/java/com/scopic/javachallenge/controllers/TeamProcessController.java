// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.dto.PlayerDto;
import com.scopic.javachallenge.dto.TeamDto;
import com.scopic.javachallenge.mappers.EntityMapper;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@Validated
public class TeamProcessController {

    private final PlayerService playerService;

    private final EntityMapper entityMapper;

    public TeamProcessController(PlayerService playerService, EntityMapper entityMapper) {
        this.playerService = playerService;
        this.entityMapper = entityMapper;
    }

    @PostMapping("/team/process")
    @Validated
    public ResponseEntity<List<PlayerDto>> create(@RequestBody List<@Valid TeamDto> teams) {

        validateThatPlayerPositionNotRepeated(teams);

        List<Player> players = playerService.selectTeam(teams);
        return ResponseEntity.ok(entityMapper.mapAsList(players, PlayerDto.class));
    }

    private void validateThatPlayerPositionNotRepeated(List<TeamDto> teams) {
        Set<String> playerPositionSet = new HashSet<>();
        teams.forEach(team -> {
            if (playerPositionSet.contains(team.getPosition())) {
                throw new IllegalArgumentException("The position of the player should not be repeated in the request.");
            }
            playerPositionSet.add(team.getPosition());
        });
    }
}
