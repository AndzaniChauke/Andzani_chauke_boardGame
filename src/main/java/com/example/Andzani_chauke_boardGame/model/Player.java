package com.example.Andzani_chauke_boardGame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

    public int treasury;
    String name;
    public int[] pits;
}
