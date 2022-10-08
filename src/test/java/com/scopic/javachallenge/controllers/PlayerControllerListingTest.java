// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import com.scopic.javachallenge.models.Player;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PlayerControllerListingTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(get(PLAYER_URL))
                .andExpect(content().string(CoreMatchers.notNullValue()));
    }

    @Test
    public void testListPlayers() throws Exception {
        List<Player> playerList = new ArrayList<>();
        playerList.add(createPlayerWithId(1L));
        playerList.add(createPlayerWithId(2L));
        playerList.add(createPlayerWithId(3L));

        given(playerService.getAll()).willReturn(playerList);

        mvc.perform(
                get(PLAYER_URL)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
    }
}
