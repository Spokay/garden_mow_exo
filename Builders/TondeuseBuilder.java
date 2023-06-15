package Builders;

import Configuration.GardenMowConfiguration;
import Models.Tondeuse;
import Utils.GardenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
public class TondeuseBuilder {
    public static ArrayList<Tondeuse> buildAll(){
        ArrayList<Tondeuse> tondeuses = new ArrayList<>();

        IntStream.range(1, GardenMowConfiguration.NB_TONDEUSES + 1).forEach(i -> {
            HashMap<String, Integer> tondeuseOrigin = GardenUtils.getRandomCoordsNotCrossingTondeuse(GardenMowConfiguration.JARDIN_MAX_HEIGHT, GardenMowConfiguration.JARDIN_MAX_WIDTH, tondeuses);
            System.out.println(tondeuseOrigin);
            /*IntStream.range(1, tondeuseWidth).forEach(tondeuseCase -> {
                HashMap<String, Integer> tondeuseCaseCoords = new HashMap<>();
                coords.add(GardenUtils.getRandomCoordsNotCrossingTondeuse();
            });*/
//            HashMap<String, Integer>
//            coords.add();
            ArrayList<HashMap<String, Integer>> coords = new ArrayList<>();
            tondeuses.add(new Tondeuse(i,coords));
        });
        return tondeuses;
    }

}
