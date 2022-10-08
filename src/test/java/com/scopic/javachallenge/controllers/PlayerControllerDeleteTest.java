// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlayerControllerDeleteTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(
                delete(PLAYER_URL + 1))
            .andExpect(content().string(CoreMatchers.notNullValue()));
    }

    @Test
    public void testDeletePlayerWithoutBearerToken() throws Exception {
        doNothing().when(playerService).delete(any());

        mvc.perform(
                delete(PLAYER_URL + 1)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeletePlayerWithBearerToken() throws Exception {
        doNothing().when(playerService).delete(any());

        mvc.perform(
                delete(PLAYER_URL + 1)
                    .header("Authorization", "Bearer SkFabTZibXE1aE14ckpQUUxHc2dnQ2RzdlFRTTM2NFE2cGI4d3RQNjZmdEFITmdBQkE=")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
