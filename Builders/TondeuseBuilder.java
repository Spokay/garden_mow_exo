package Builders;

import Configuration.GardenMowConfiguration;
import Models.Obstacles.Obstacle;
import Models.Obstacles.ObstaclesTypes;
import Models.Obstacles.Tondeuse;
import Utils.GardenUtils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
public class TondeuseBuilder {
    public static AbstractList<Tondeuse> buildAll(HashMap<ObstaclesTypes, ArrayList<? extends Obstacle>> obstacles){
        ArrayList<Tondeuse> tondeuses = new ArrayList<>();
        // create and add to the array list instances of tondeuses
        IntStream.range(1, GardenMowConfiguration.NB_TONDEUSES + 1).forEach(i -> {
            HashMap<String, Integer> tondeuseOrigin = GardenUtils.getRandomCoordsNotCrossingObstacle(GardenMowConfiguration.JARDIN_MAX_HEIGHT, GardenMowConfiguration.JARDIN_MAX_WIDTH, obstacles);
            System.out.println(tondeuseOrigin + "tondeuse origin coords");

            ArrayList<HashMap<String, Integer>> coords = new ArrayList<>();
            tondeuses.add(new Tondeuse(i,coords));
            obstacles.put(ObstaclesTypes.TONDEUSE, tondeuses);
        });
        return tondeuses;
    }

}
