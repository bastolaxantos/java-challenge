// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.dto.PlayerDto;
import com.scopic.javachallenge.mappers.EntityMapper;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlayerController {

  private final PlayerService playerService;

  private final EntityMapper entityMapper;

  public PlayerController(PlayerService playerService, EntityMapper entityMapper) {
    this.playerService = playerService;
    this.entityMapper = entityMapper;
  }

  @GetMapping("/player")
  @ResponseBody
  public ResponseEntity<List<PlayerDto>> index() {
    List<PlayerDto> playerDtoList = entityMapper.mapAsList(playerService.getAll(), PlayerDto.class);
    return ResponseEntity.ok(playerDtoList);
  }

  @GetMapping("/player/{id}")
  public ResponseEntity<PlayerDto> show(@PathVariable final Long id) {
    PlayerDto playerDto = entityMapper.map(playerService.getById(id), PlayerDto.class);
    return ResponseEntity.ok(playerDto);
  }

  @PostMapping("/player")
  @ResponseBody
  public ResponseEntity<PlayerDto> create(@Valid @RequestBody PlayerDto playerDto) {
    Player player = entityMapper.map(playerDto, Player.class);
    PlayerDto playerFromDb = entityMapper.map(playerService.save(player), PlayerDto.class);
    return ResponseEntity.ok(playerFromDb);
  }

  @PutMapping("/player/{id}")
  public ResponseEntity<PlayerDto> update(@PathVariable final Long id, @RequestBody @Valid PlayerDto playerDto) {
    Player player = entityMapper.map(playerDto, Player.class);
    PlayerDto playerFromDb = entityMapper.map(playerService.update(player, id), PlayerDto.class);
    return ResponseEntity.ok(playerFromDb);
  }

  @DeleteMapping("/player/{id}")
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    playerService.delete(id);
    return ResponseEntity.ok().build();
  }
}
