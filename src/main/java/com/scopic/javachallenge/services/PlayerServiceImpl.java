package com.scopic.javachallenge.services;

import com.scopic.javachallenge.exceptions.InsufficientNumberOfPlayersException;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.TeamSelection;
import com.scopic.javachallenge.repositories.PlayerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
  private final PlayerRepository playerRepository;

  public PlayerServiceImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public Player save(Player player) {
    return playerRepository.save(player);
  }

  @Override
  public Player getById(Long id) {
    return playerRepository.findById(id)
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public List<Player> getAll() {
    return playerRepository.findAll();
  }

  @Override
  public Player update(Player player, Long id) {
    Player playerFromDb = getById(id);
    playerFromDb.setName(player.getName());
    playerFromDb.setPosition(player.getPosition());
    playerFromDb.setPlayerSkills(player.getPlayerSkills());
    return save(playerFromDb);
  }

  @Override
  public void delete(Long id) {
    playerRepository.deleteById(id);
  }

  @Override
  public List<Player> selectTeam(List<TeamSelection> teamSelectionList) {
    List<Player> players = new ArrayList<>();
    teamSelectionList.forEach(teamSelection -> {
      List<Player> playersFromDb = playerRepository.findSkilledPlayerByPositionAndSkill(
          teamSelection.getPosition(),
          teamSelection.getMainSkill(),
          PageRequest.of(0, teamSelection.getNumberOfPlayers()));

      if (playersFromDb.size() < teamSelection.getNumberOfPlayers()) {
        // get most skilled for any skill
        List<Long> alreadySelectedIds = playersFromDb.stream()
            .map(Player::getId)
            .collect(Collectors.toList());

        playersFromDb.addAll(playerRepository.findSkilledPlayerByPosition(
            teamSelection.getPosition(),
            alreadySelectedIds,
            PageRequest.of(0, teamSelection.getNumberOfPlayers() - playersFromDb.size())));
      }

      if (playersFromDb.size() < teamSelection.getNumberOfPlayers()) {
        throw new InsufficientNumberOfPlayersException("Insufficient number of players for position: " + teamSelection.getPosition());
      }

      players.addAll(playersFromDb);
    });
    return players;
  }
}
