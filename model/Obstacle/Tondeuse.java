package model.Obstacle;

import model.Case.Case;
import model.Case.CaseStatus;
import util.GardenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        // initialize a hashmap
        HashMap<String, Integer> nearestCoords = new HashMap<>();

        // set the min coords found to an unreachable integer
        int currentMinDifference = 999;

        // get all the remaining CaseHerbe
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseStatus.CASE_HERBE, cases);


        for (Case operatedCase : herbeCases) {
            // compare the tondeuse coords total difference to each CaseHerbe
            int totalDiffToCase = Math.abs(GardenUtils.getTondeuseYDiffToCase(tondeuse, operatedCase) - GardenUtils.getTondeuseXDiffToCase(tondeuse, operatedCase));

            // When a coord is the nearest found yet reset the variables with the new smaller difference value and store the nearest coords
            if (totalDiffToCase < currentMinDifference){
                nearestCoords = operatedCase.getCoords();
                currentMinDifference = totalDiffToCase;
            }
        }

        return cases[nearestCoords.get("Y")][nearestCoords.get("X")];
    }

}
