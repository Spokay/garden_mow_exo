package builder;

import model.Case;
import model.Obstacles.Obstacle;
import model.Obstacles.ObstaclesTypes;
import model.Obstacles.Tondeuse;

import java.util.ArrayList;
import java.util.HashMap;

public class CaseBuilder {
    public static Case[][] buildAll(ArrayList<Tondeuse> tondeuses){
        HashMap<ObstaclesTypes, ArrayList<? extends Obstacle>> obstacles = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Case>> cases = new HashMap<>();
        tondeuses.addAll(TondeuseBuilder.buildAll(cases));

        return cases;
    }

    public HashMap<Integer, HashMap<Integer, Case>> buildCaseOccupee(){
        return null;
    }

}
