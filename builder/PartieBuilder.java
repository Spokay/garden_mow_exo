package builder;

import configuration.GardenMowConfiguration;
import model.*;
import model.Obstacles.Tondeuse;

import java.util.ArrayList;
import java.util.HashMap;

public class PartieBuilder {
    public static Partie build(){
        ArrayList<Tondeuse> tondeuses = new ArrayList<>();
        Case[][] cases = CaseBuilder.buildAll(tondeuses);

        return new Partie(GardenMowConfiguration.JARDIN_MAX_WIDTH, GardenMowConfiguration.JARDIN_MAX_HEIGHT, tondeuses, cases);
    }
}
