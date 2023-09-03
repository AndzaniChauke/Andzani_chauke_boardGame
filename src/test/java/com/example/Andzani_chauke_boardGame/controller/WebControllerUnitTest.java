package com.example.Andzani_chauke_boardGame.controller;

import com.example.Andzani_chauke_boardGame.model.Game;
import com.example.Andzani_chauke_boardGame.model.Movement;
import com.example.Andzani_chauke_boardGame.service.GameService;
import com.example.Andzani_chauke_boardGame.util.BaseUnitTest;
import com.example.Andzani_chauke_boardGame.util.RequestRouting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WebControllerUnitTest extends BaseUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private WebController gameController;
    @Mock
    private GameService gameService;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void testGetStart() throws Exception {
        // Mock the behavior of gameService.getGame
        Mockito.when(gameService.getGame(Mockito.anyString())).thenReturn(getGame());

        // Perform the GET request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(RequestRouting.START)
                        .param("gameId", "your-game-id"))
                .andExpect(status().isOk())
                .andReturn();


        String content = result.getResponse().getContentAsString();

        Mockito.verify(gameService).getGame("your-game-id");
    }



}

