package Builders;

import Configuration.GardenMowConfiguration;
import Models.*;
import Models.Obstacles.Obstacle;
import Models.Obstacles.ObstaclesTypes;
import Models.Obstacles.Tondeuse;

import java.util.ArrayList;
import java.util.HashMap;

public class PartieBuilder {
    public static Partie build(){
        HashMap<ObstaclesTypes, ArrayList<? extends Obstacle>> obstacles = new HashMap<>();
        ArrayList<Tondeuse> tondeuses = (ArrayList<Tondeuse>) TondeuseBuilder.buildAll(obstacles);

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
