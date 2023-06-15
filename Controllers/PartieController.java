package Controllers;

import Builders.PartieBuilder;
import Models.Partie;

public class PartieController {

    public void startGame(){
        Partie game = PartieBuilder.build();
    }
}
