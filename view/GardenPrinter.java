package view;

import configuration.GardenMowConfiguration;
import model.Jardin;

import java.util.ArrayList;

public class GardenPrinter {
    public static void printGameReport(ArrayList<Jardin> turnsGardens, boolean isFinished) {
        turnsGardens.forEach(garden -> {
            System.out.println(JardinStringGenerator.generateTurnString(garden.getGardenTurn()));
            System.out.println(JardinStringGenerator.generateJardinString(garden));

        });
        if(isFinished){
                System.out.println("\nYou have mown the garden completely in " + (turnsGardens.size() - 1) + " turns");
        }else{
            System.out.println("You have failed to mown the garden in the maximum allowed turns ("+ GardenMowConfiguration.MAX_TURNS +")");
        }
    }
}
