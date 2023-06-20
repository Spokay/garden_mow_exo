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
    public static Case[][] buildAll(ArrayList<Tondeuse> tondeuses){

        // initialize a 2d array of cases
        Case[][] cases = new Case[GardenMowConfiguration.JARDIN_MAX_HEIGHT][GardenMowConfiguration.JARDIN_MAX_WIDTH];

        // first build normal cases
        IntStream.range(0, GardenMowConfiguration.JARDIN_MAX_HEIGHT)
                .forEach(row -> IntStream.range(0, GardenMowConfiguration.JARDIN_MAX_WIDTH)
                    .forEach(column -> {
                        System.out.println(row + " " + column);
                        HashMap<String, Integer> coords = new HashMap<>();
                        cases[row][column] = new CaseHerbe(coords);
                    })
                );
        // then build occupied cases with the help of TondeuseBuilder.buildAll() and fill the tondeuses array

        tondeuses.addAll(TondeuseBuilder.buildAll(cases));

        tondeuses.forEach(tondeuse -> {
            tondeuse.getCoords().forEach(tondeuseCoords -> cases[tondeuseCoords.get("Y")][tondeuseCoords.get("X")] = new CaseOccupee(tondeuseCoords, tondeuse));
        });

        System.out.println(Arrays.deepToString(cases));

        return cases;

    }

}
