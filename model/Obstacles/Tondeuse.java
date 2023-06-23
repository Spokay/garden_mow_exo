package model.Obstacles;

import model.Case;
import model.CaseHerbe;

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
        ArrayList<Case> operatedCases = new ArrayList<>();

        Arrays.stream(cases)
                .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> caseJ instanceof CaseHerbe)
                        .forEach(operatedCases::add)
                );

        System.out.println(operatedCases);

        for (Case operatedCase : operatedCases) {
            int totalDiffToCase = Math.abs(operatedCase.getCoords().get("Y") - tondeuse.getCoords().get("Y")) + Math.abs(operatedCase.getCoords().get("X") - tondeuse.getCoords().get("X"));

            if (totalDiffToCase < currentMinDifference){
                nearestCoords = operatedCase.getCoords();
                currentMinDifference = totalDiffToCase;
            }
        }
        System.out.println(currentMinDifference);

        return cases[nearestCoords.get("Y")][nearestCoords.get("X")];
    }

}
