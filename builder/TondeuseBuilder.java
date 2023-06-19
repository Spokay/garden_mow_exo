package builder;

import configuration.GardenMowConfiguration;
import model.Case;
import model.CaseOccupee;
import model.Obstacles.Tondeuse;
import util.GardenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;
public class TondeuseBuilder {
    public static ArrayList<Tondeuse> buildAll(HashMap<Integer, HashMap<Integer, Case>> cases){
        ArrayList<Tondeuse> tondeuses = new ArrayList<>();
        // create and add to the array list instances of tondeuses
        IntStream.range(1, GardenMowConfiguration.NB_TONDEUSES + 1).forEach(i -> {
            int[][] coords = GardenUtils.getRandomCoordsNotCrossingObstacle(GardenMowConfiguration.JARDIN_MAX_HEIGHT, GardenMowConfiguration.JARDIN_MAX_WIDTH, cases);
            tondeuses.add(new Tondeuse(i,coords));
            cases.get(tondeuseOrigin.get("Y")).put(tondeuseOrigin.get("X"), new CaseOccupee());
        });
        return tondeuses;
    }

}
