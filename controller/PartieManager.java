package controller;

import builder.PartieBuilder;
import model.Partie;

public class PartieManager {
    public Partie currentGame;

    public void startGame(){
        this.currentGame = PartieBuilder.build();
        while(!this.currentGame.isFinished){
            this.nextTurn();
        }
    }

    private void nextTurn() {
        this.currentGame.moveTondeusesToClosestCaseHerbe();
    }


}
