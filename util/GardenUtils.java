package util;

import model.Case;
import model.CaseOccupee;

import java.util.HashMap;
import java.util.Random;

public class GardenUtils {
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
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
}
