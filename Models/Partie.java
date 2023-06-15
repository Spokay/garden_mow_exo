package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Partie {
    Integer xLength;
    Integer yLength;
    HashMap<Integer, HashMap<Integer, Case>> cases;
    ArrayList<Tondeuse> tondeuses;

    ArrayList<Jardin> turnsGardens;

    public Partie(Integer xLength, Integer yLength, ArrayList<Tondeuse> tondeuses, HashMap<Integer, HashMap<Integer, Case>> cases) {
        this.xLength = xLength;
        this.yLength = yLength;
        this.tondeuses = tondeuses;
        this.cases = cases;
        this.turnsGardens = new ArrayList<>();
    }

    public Case getCaseByCoords(Integer xValue, Integer yValue) {
        return this.cases.get(yValue).get(xValue);
    }
}
