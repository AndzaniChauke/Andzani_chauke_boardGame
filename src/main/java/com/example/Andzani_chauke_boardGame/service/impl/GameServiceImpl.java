package com.example.Andzani_chauke_boardGame.service.impl;

import com.example.Andzani_chauke_boardGame.model.Game;
import com.example.Andzani_chauke_boardGame.model.Movement;
import com.example.Andzani_chauke_boardGame.model.Player;
import com.example.Andzani_chauke_boardGame.service.GameService;
import com.example.Andzani_chauke_boardGame.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class GameServiceImpl  implements GameService {

    private HashMap<String, Game> games = new HashMap<>();

    @Override
    public Game getGame(String gameId) {
        if (games.containsKey(gameId)) {
            return games.get(gameId);
        }
        return games.get(createNewGame());
    }

    @Override
    public Game getMove(Movement movement) {
        Game game = this.getGame(movement.getGameId());
        if (!game.getCurrentPlayer().equals(movement.getPlayer())) return game;

        Game tempGame = game;
        int pitNumber = movement.getPitNumber();

        boolean isCurrentsTurn = false;
        switch (movement.getPlayer()) {
            case Constants.PLAYER1_KEY:

                isCurrentsTurn = calculateMovements(tempGame, pitNumber);
                if (!isCurrentsTurn) {
                    tempGame.setCurrentPlayer(Constants.PLAYER2_KEY);
                }

                break;
            case Constants.PLAYER2_KEY:
                Player player1 = game.getPlayer1();
                Player player2 = game.getPlayer2();
                tempGame.setPlayer1(player2);
                tempGame.setPlayer2(player1);
                isCurrentsTurn = calculateMovements(tempGame, pitNumber);
                if (!isCurrentsTurn) {
                    tempGame.setCurrentPlayer(Constants.PLAYER1_KEY);
                }
                player1 = tempGame.getPlayer2();
                player2 = tempGame.getPlayer1();
                tempGame.setPlayer1(player1);
                tempGame.setPlayer2(player2);

                break;
            default:
                return game;
        }
        boolean isWinnerExist = checkWinner(tempGame);
        tempGame.setWinnerExist(isWinnerExist);
        game = tempGame;
        return game;
    }

    private String createNewGame() {
        UUID uuid = UUID.randomUUID();
        String gameId = uuid.toString();
        games.put(gameId, this.initiateResultSet(gameId));
        return gameId;
    }

    /**
     *
     * @param game current Game details
     * @param pitNumber selected Pi tNumber
     * @return
     */
    private boolean calculateMovements(Game game, int pitNumber) {
        Player activePlayer = game.getPlayer1();
        Player counterPlayer = game.getPlayer2();

        int[] ownPits = activePlayer.getPits();
        int stones = ownPits[pitNumber];
        boolean isMyTurn = false;


        ownPits[pitNumber] = Constants.BLANK;
        if (stones == Constants.BLANK) return true;
        while (stones != Constants.BLANK) {
            for (int i = pitNumber + 1; i < Constants.PIT; i++) {
                if (stones == Constants.BLANK) break;
                ownPits[i]++;
                stones--;
            }

            if (stones != Constants.BLANK) {
                isMyTurn = true;
                activePlayer.treasury++;
                stones--;
            }

            if (stones != Constants.BLANK) {
                isMyTurn = false;
                int[] counterPits = counterPlayer.getPits();

                int targetIndex = (stones >= Constants.PIT) ? Constants.PIT : stones;

                for (int i = 0; i < targetIndex; i++) {
                    if (stones == Constants.BLANK) break;
                    counterPits[i]++;
                    stones--;
                }
                if (counterPits[targetIndex - 1] % 2 == 0 && counterPits[targetIndex - 1] > 0) {
                    activePlayer.treasury += counterPits[targetIndex];
                    activePlayer.treasury += ownPits[Constants.PIT - targetIndex];
                    ownPits[Constants.PIT - targetIndex] = 0;
                    counterPits[targetIndex - 1] = 0;

                }
                counterPlayer.setPits(counterPits);
            }
            activePlayer.setPits(ownPits);
        }

        game.setPlayer1(activePlayer);
        game.setPlayer2(counterPlayer);
        return isMyTurn;
    }

    private Game initiateResultSet(String gameId) {
        int[] pits1 = new int[Constants.PIT];
        int[] pits2 = new int[Constants.PIT];

        Arrays.fill(pits1, Constants.STONE);
        Arrays.fill(pits2, Constants.STONE);

        return Game.builder().
                gameId(gameId).
                currentPlayer(Constants.PLAYER1_KEY).
                player1(Player.builder().pits(pits1).build()).
                player2(Player.builder().pits(pits2).build()).
                build();
    }

    private boolean checkWinner(Game game) {

        boolean isWinnerExist = isAllStonesSpent(game.getPlayer1().getPits())
                || isAllStonesSpent(game.getPlayer2().getPits());

        if (isWinnerExist) {
            decideWinner(game);
        }
        return isWinnerExist;
    }

    private void decideWinner(Game game) {
        int player1Treasury = game.getPlayer1().treasury +
                Arrays.stream(game.getPlayer1().getPits()).sum();
        int player2Treasury = game.getPlayer2().treasury +
                Arrays.stream(game.getPlayer2().getPits()).sum();
        if (player1Treasury > player2Treasury) {

            game.setWinner(Constants.PLAYER1_KEY);
        } else {

            game.setWinner(Constants.PLAYER2_KEY);
        }
    }

    private static boolean isAllStonesSpent(int[] pits) {
        for (int pit : pits) if (pit != 0) return false;
        return true;
    }
}
