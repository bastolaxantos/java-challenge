// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.dto.TeamDto;
import com.scopic.javachallenge.models.Player;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TeamProcessControllerTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {

        mvc.perform(
                post(TEAM_URL))
            .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    public void testTeamSelection() throws Exception {
        List<TeamDto> teamDtoList = new ArrayList<>();
        teamDtoList.add(new TeamDto("midfiender", "defense", 1));

        List<Player> players = new ArrayList<>();
        players.add(createPlayer());

        given(playerService.selectTeam(any())).willReturn(players);

        mvc.perform(post(TEAM_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(teamDtoList)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));
    }
}