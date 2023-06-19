package controller;

import builder.PartieBuilder;
import model.Partie;

public class PartieController {

    public void startGame(){
        Partie game = PartieBuilder.build();
    }
}
