package Builders;

import Configuration.GardenMowConfiguration;
import Models.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PartieBuilder {
    public static Partie build(){

        ArrayList<Tondeuse> tondeuses = TondeuseBuilder.buildAll();

        HashMap<Integer, HashMap<Integer, Case>> cases = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            HashMap<Integer, Case> row = new HashMap<>();
            for (int j = 1; j < 5; j++) {
                if (j % 2 == 0){
                    row.put(j, new CaseHerbe(i, j));
                }else {
                    row.put(j, new CaseTondue(i, j));
                }
            }
            cases.put(i, row);
        }

        return new Partie(GardenMowConfiguration.JARDIN_MAX_WIDTH, GardenMowConfiguration.JARDIN_MAX_HEIGHT, tondeuses, cases);
    }
}
