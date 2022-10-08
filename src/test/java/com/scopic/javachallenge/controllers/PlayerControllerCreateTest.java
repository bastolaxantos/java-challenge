// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.dto.PlayerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlayerControllerCreateTest extends BasePlayerControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Test
  public void testSample() throws Exception {
    mvc.perform(
            post(PLAYER_URL)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(content().string(notNullValue()));
  }

  @Test
  public void testCreatePlayer() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    PlayerDto player = createPlayerDto();

    given(playerService.save(any())).willReturn(createPlayer());

    mvc.perform(
            post(PLAYER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(player))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(player.getName())))
        .andExpect(jsonPath("$.id", notNullValue(Long.class)))
        .andExpect(jsonPath("$.playerSkills[0].id", notNullValue(Long.class)));
  }
}