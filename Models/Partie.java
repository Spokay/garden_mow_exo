package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Partie {
    Integer xLength;
    Integer yLength;
    HashMap<Integer, HashMap<Integer, Case>> cases;
    ArrayList<Tondeuse> tondeuses;

    ArrayList<Jardin> turnsGardens;

    public Case getCaseByCoords(Integer xValue, Integer yValue) {
        return this.cases.get(yValue).get(xValue);
    }
}
