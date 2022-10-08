// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.dto.PlayerDto;
import com.scopic.javachallenge.models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PlayerControllerUpdateTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(get(PLAYER_URL + 1))
            .andExpect(content().string(notNullValue()));
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        Player player = createPlayer();

        Player updatedPlayer = createPlayer();
        updatedPlayer.setName("Alice");

        PlayerDto playerDto = createPlayerDtoWithId();
        playerDto.setName("Alice");

        given(playerService.getById(any())).willReturn(player);
        given(playerService.update(any(), any())).willReturn(updatedPlayer);

        mvc.perform(
                put(PLAYER_URL + 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(playerDto))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(playerDto.getName())))
            .andExpect(jsonPath("$.id", is(playerDto.getId()), Long.class))
            .andExpect(jsonPath("$.playerSkills[0].id", notNullValue(Long.class)));
    }
}
