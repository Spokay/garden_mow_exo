package controller;

import builder.PartieBuilder;
import model.Partie;

public class PartieController {
    public Partie currentGame;

    public void startGame(){
        this.currentGame = PartieBuilder.build();
    }
}
