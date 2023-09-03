package com.example.Andzani_chauke_boardGame.controller;

import com.example.Andzani_chauke_boardGame.model.Game;
import com.example.Andzani_chauke_boardGame.service.GameService;
import com.example.Andzani_chauke_boardGame.util.BaseUnitTest;
import com.example.Andzani_chauke_boardGame.util.Constants;
import com.example.Andzani_chauke_boardGame.util.ModelAttributes;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



class ViewControllerUnitTest extends BaseUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ViewController underTest;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Mock
    private GameService gameService;


    @Test
    public void testGetStartInvalidInput() throws Exception {
        this.mockMvc.perform(post(RequestRouting.GAME)
                        .param("name", "") // Set invalid input here
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) // Assuming you return HttpStatus.OK
                .andExpect(MockMvcResultMatchers.view().name(Constants.HOME))

                .andExpect(MockMvcResultMatchers.model().hasErrors());


    }
}