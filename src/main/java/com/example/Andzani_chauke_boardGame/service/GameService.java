package com.example.Andzani_chauke_boardGame.service;

import com.example.Andzani_chauke_boardGame.model.Game;
import com.example.Andzani_chauke_boardGame.model.Movement;

public interface GameService {
    Game getGame(String gameId);

    Game getMove(Movement movement);
}
