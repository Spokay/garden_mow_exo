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
    public static ArrayList<Tondeuse> buildAll(Case[][] cases){
        ArrayList<Tondeuse> tondeuses = new ArrayList<>();
        // create and add to the array list instances of tondeuses
        IntStream.range(0, GardenMowConfiguration.NB_TONDEUSES).forEach(i -> {
            HashMap<String, Integer> coords = GardenUtils.getRandomCoordsNotCrossingObstacle(GardenMowConfiguration.JARDIN_MAX_HEIGHT, GardenMowConfiguration.JARDIN_MAX_WIDTH, cases);

            Tondeuse tondeuse = new Tondeuse(i + 1,coords);

            tondeuses.add(tondeuse);

            cases[tondeuse.getCoords().get("Y")][tondeuse.getCoords().get("X")] = new CaseOccupee(tondeuse.getCoords(), tondeuse);
        });
        return tondeuses;
    }

}
