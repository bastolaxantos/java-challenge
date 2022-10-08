// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scopic.javachallenge.dto.PlayerDto;
import com.scopic.javachallenge.dto.PlayerSkillDto;
import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;
import com.scopic.javachallenge.repositories.PlayerRepository;
import com.scopic.javachallenge.services.PlayerService;
import de.cronn.testutils.h2.H2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@Import(H2Util.class)
public abstract class BasePlayerControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PlayerRepository playerRepository;

    @MockBean
    PlayerService playerService;

    final static String PLAYER_URL = "/player/";

    final static String TEAM_URL = "/team/process";

    protected String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected PlayerDto createPlayerDto() {
        Set<PlayerSkillDto> playerSkillDtoSet = new HashSet<>();
        playerSkillDtoSet.add(new PlayerSkillDto(null, "defense", 57, 1L));
        playerSkillDtoSet.add(new PlayerSkillDto(null, "stamina", 83, 1L));

        return new PlayerDto(null, "John Doe", "midfielder", playerSkillDtoSet);
    }

    protected PlayerDto createPlayerDtoWithId() {
        Set<PlayerSkillDto> playerSkillDtoSet = new HashSet<>();
        playerSkillDtoSet.add(new PlayerSkillDto((long) 1, "defense", 57, 1L));
        playerSkillDtoSet.add(new PlayerSkillDto((long) 2, "stamina", 83, 1L));

        return new PlayerDto(1L, "John Doe", "midfielder", playerSkillDtoSet);
    }

    protected Player createPlayer() {
        return createPlayerWithId(1L);
    }

    protected Player createPlayerWithId(Long id) {
        Player player = new Player();
        player.setId(id);
        player.setName("John Doe");
        player.setPosition(PlayerPosition.MIDFIELDER);

        Set<PlayerSkill> playerSkills = new HashSet<>();
        playerSkills.add(new PlayerSkill(id, Skill.DEFENSE, 57, player));
        playerSkills.add(new PlayerSkill(id + 1, Skill.STAMINA, 83, player));

        player.setPlayerSkills(playerSkills);
        return player;
    }
}
