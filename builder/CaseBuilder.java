package builder;

import configuration.GardenMowConfiguration;
import model.Case;
import model.CaseHerbe;
import model.CaseOccupee;
import model.Obstacles.Tondeuse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class CaseBuilder {
    public static Case[][] buildField() {

        // initialize a 2d array of cases
        Case[][] cases = new Case[GardenMowConfiguration.JARDIN_MAX_HEIGHT][GardenMowConfiguration.JARDIN_MAX_WIDTH];

        // build normal cases
        IntStream.range(0, GardenMowConfiguration.JARDIN_MAX_HEIGHT)
                .forEach(row -> IntStream.range(0, GardenMowConfiguration.JARDIN_MAX_WIDTH)
                        .forEach(column -> {
                            HashMap<String, Integer> coords = new HashMap<>();
                            cases[row][column] = new CaseHerbe(coords);
                        })
                );


        return cases;

    }

}
