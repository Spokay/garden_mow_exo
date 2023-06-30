package builder;

import configuration.GardenMowConfiguration;
import model.Case.Case;
import model.Jardin;
import model.Obstacle.Tondeuse;
import model.Partie;

import java.util.ArrayList;

public class PartieBuilder {
    public static Partie build(){
        Case[][] cases = CaseBuilder.buildField();
        ArrayList<Tondeuse> tondeuses = TondeuseBuilder.buildAll(cases);

        Jardin initialJardin = new Jardin(CaseBuilder.buildCopyOfCases(cases), 0);
        ArrayList<Jardin> turnsGardens = new ArrayList<>();
        turnsGardens.add(initialJardin);

        return new Partie(GardenMowConfiguration.JARDIN_MAX_WIDTH, GardenMowConfiguration.JARDIN_MAX_HEIGHT, tondeuses, cases, turnsGardens);
    }
}
