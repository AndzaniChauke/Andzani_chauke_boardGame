package com.example.Andzani_chauke_boardGame.controller;

import com.example.Andzani_chauke_boardGame.model.Game;
import com.example.Andzani_chauke_boardGame.model.Movement;
import com.example.Andzani_chauke_boardGame.service.GameService;
import com.example.Andzani_chauke_boardGame.util.RequestRouting;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WebController {

    @Autowired
    private GameService gameService;

    /**
     * This action is called in js file to start the game
     * @param gameId used to identify game session
     * @return
     */
    @GetMapping(RequestRouting.START)
    @ResponseBody
    public Game getStart( @RequestParam String gameId) {

        return gameService.getGame(gameId);

    }


    /**
     * This action is called in js file everytime we move
     * @param movement to identify players move
     * @return
     */
    @PostMapping(RequestRouting.MOVE)
    public Game getMovement(@Valid @RequestBody Movement movement) {
        return gameService.getMove(movement);
    }

}
