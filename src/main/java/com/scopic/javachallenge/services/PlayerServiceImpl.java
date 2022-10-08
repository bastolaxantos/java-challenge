package com.scopic.javachallenge.services;

import com.scopic.javachallenge.dto.TeamDto;
import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.exceptions.InsufficientNumberOfPlayersException;
import com.scopic.javachallenge.exceptions.PlayerNotFoundException;
import com.scopic.javachallenge.models.Player;
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
        player.getPlayerSkills().forEach(playerSkill -> playerSkill.setPlayer(player));

        return playerRepository.save(player);
    }

    @Override
    public Player getById(Long id) {
        return playerRepository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
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
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException("Player with id " + id + " not found");
        }
        playerRepository.deleteById(id);
    }

    @Override
    public List<Player> selectTeam(List<TeamDto> teamList) {
        List<Player> players = new ArrayList<>();
        teamList.forEach(team -> {
            List<Player> playersFromDb = playerRepository.findSkilledPlayerByPositionAndSkill(
                PlayerPosition.valueOf(team.getPosition().toUpperCase()),
                Skill.valueOf(team.getMainSkill().toUpperCase()),
                PageRequest.of(0, team.getNumberOfPlayers()));

            if (playersFromDb.size() < team.getNumberOfPlayers()) {
                // get most skilled for any skill
                List<Long> alreadySelectedIds = playersFromDb.stream()
                    .map(Player::getId)
                    .collect(Collectors.toList());

                playersFromDb.addAll(playerRepository.findSkilledPlayerByPosition(
                    PlayerPosition.valueOf(team.getPosition().toUpperCase()),
                    alreadySelectedIds,
                    PageRequest.of(0, team.getNumberOfPlayers() - playersFromDb.size())));
            }

            if (playersFromDb.size() < team.getNumberOfPlayers()) {
                throw new InsufficientNumberOfPlayersException("Insufficient number of players for position: " + team.getPosition());
            }

            players.addAll(playersFromDb);
        });
        return players;
    }
}
