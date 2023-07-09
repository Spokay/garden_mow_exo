package util;

import model.Case.Case;
import model.Case.CaseOccupee;
import model.Case.CaseStatus;
import model.Obstacle.Tondeuse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GardenUtils {
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) - 1) + min;

    }

    public static HashMap<String, Integer> getRandomCoordsNotCrossingObstacle(Integer yMaxValue, Integer xMaxValue, Case[][] cases){
        // get randoms coords
        int xCoord = getRandomNumberUsingNextInt(0, xMaxValue);
        int yCoord = getRandomNumberUsingNextInt(0, yMaxValue);

        HashMap<String, Integer> coords = new HashMap<>();
        coords.put("Y", yCoord);
        coords.put("X", xCoord);
        // keep getting new coords until a valid one is available
        while(isCaseOccupee(coords, cases)){
            coords.replace("Y", getRandomNumberUsingNextInt(0, yMaxValue));
            coords.replace("X", getRandomNumberUsingNextInt(0, xMaxValue));
        }

        return coords;
    }

    public static boolean isCaseOccupee(HashMap<String, Integer> coords, Case[][] cases){
        // check whether the case is occupied or not
        return cases[coords.get("Y")][coords.get("X")] instanceof CaseOccupee;
    }

    public static ArrayList<Case> getAllCaseByType(CaseStatus caseType, Case[][] allCases){
        ArrayList<Case> operatedCases = new ArrayList<>();
        Arrays.stream(allCases)
                .forEach(caseArr -> Arrays.stream(caseArr)
                        .filter(caseJ -> caseJ.getCaseType().equals(caseType))
                        .forEach(operatedCases::add)
                );
        return operatedCases;
    }

    public static Integer getTondeuseYDiffToCase(Tondeuse tondeuse, Case caseToCheck){
        return (caseToCheck.getCoords().get("Y") - tondeuse.getCoords().get("Y"));
    }
    public static Integer getTondeuseXDiffToCase(Tondeuse tondeuse, Case caseToCheck){
        return (caseToCheck.getCoords().get("X") - tondeuse.getCoords().get("X"));
    }
}
