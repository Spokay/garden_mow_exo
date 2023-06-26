package model.Obstacles;

import model.Case;
import model.CaseHerbe;
import model.CaseTypes;
import util.GardenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Tondeuse implements Obstacle{
    Integer tondeuseNumber;
    HashMap<String, Integer> coords  = new HashMap<>();

    public Tondeuse(Integer tondeuseNumber, HashMap<String, Integer> coordsOrigin) {
        this.setTondeuseNumber(tondeuseNumber);
        this.setCoords(coordsOrigin);
    }

    public Integer getTondeuseNumber() {
        return tondeuseNumber;
    }

    public void setTondeuseNumber(Integer tondeuseNumber) {
        this.tondeuseNumber = tondeuseNumber;
    }

    public HashMap<String, Integer> getCoords() {
        return coords;
    }

    public void setCoords(HashMap<String, Integer> coords) {
        this.coords = coords;
    }

    @Override
    public ArrayList<String> getObstacleAppearance() {
        return new ArrayList<>(
                Arrays.asList(
                        "        ",
                        "   T".concat(String.valueOf(this.getTondeuseNumber())).concat("   "),
                        "        "
                )
        );
    }

    public Case searchForNearestCaseHerbe(Case[][] cases, Tondeuse tondeuse) {
        HashMap<String, Integer> nearestCoords = new HashMap<>();
        int currentMinDifference = 999;
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseTypes.CASE_HERBE, cases);


        for (Case operatedCase : herbeCases) {
            int totalDiffToCase = Math.abs(GardenUtils.getTondeuseYDiffToCase(tondeuse, operatedCase) - GardenUtils.getTondeuseXDiffToCase(tondeuse, operatedCase));

            if (totalDiffToCase < currentMinDifference){
                nearestCoords = operatedCase.getCoords();
                currentMinDifference = totalDiffToCase;
            }
        }

        return cases[nearestCoords.get("Y")][nearestCoords.get("X")];
    }

}
