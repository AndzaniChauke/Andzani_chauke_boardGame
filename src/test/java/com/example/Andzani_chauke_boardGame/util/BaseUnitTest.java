package com.example.Andzani_chauke_boardGame.util;

import com.example.Andzani_chauke_boardGame.form.NameForm;
import com.example.Andzani_chauke_boardGame.model.Game;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
public class BaseUnitTest {

    /**
     * Returns populated game
     * @return
     */
    public static Game getGame(){
        Game game=new Game();
        game.setGameId("123");
        game.setCurrentPlayer("Player1");
        game.setWinner("Player2");
        return game;

    }

    /**
     * Returns populated name form
     * @return
     */
    public NameForm getNameForm(){
        NameForm nameForm=new NameForm();
        nameForm.setPlayer1("Andzani");
        nameForm.setPlayer2("Andy");
        return nameForm;
    }

}