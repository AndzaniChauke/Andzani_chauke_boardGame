package com.example.Andzani_chauke_boardGame.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class NameForm {

    @NotEmpty(message = "Player one name cannot be empty!")
    private  String player1;
    @NotEmpty(message = "Player two name cannot be empty!")
    private  String player2;

}
