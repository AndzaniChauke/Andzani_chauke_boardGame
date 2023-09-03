package com.example.Andzani_chauke_boardGame.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class Game {
    public String gameId;
    public String currentPlayer;
    public boolean isWinnerExist;
    public String winner;
    public Player player1;
    public Player player2;
}
