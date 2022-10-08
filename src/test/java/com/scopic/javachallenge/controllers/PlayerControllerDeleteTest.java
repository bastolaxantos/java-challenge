// /////////////////////////////////////////////////////////////////////////////
// TESTING AREA
// THIS IS AN AREA WHERE YOU CAN TEST YOUR WORK AND WRITE YOUR TESTS
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.controllers;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class PlayerControllerDeleteTest extends BasePlayerControllerTest {

    @Test
    public void testSample() throws Exception {
        mvc.perform(
                delete(PLAYER_URL + 1))
            .andExpect(content().string(CoreMatchers.notNullValue()));
    }

    @Test
    public void testDeletePlayer() throws Exception {
        doNothing().when(playerService).delete(any());

        mvc.perform(
                delete(PLAYER_URL + 1)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
