package Utils;

import Models.Tondeuse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GardenUtils {
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static HashMap<String, Integer> getRandomCoordsNotCrossingTondeuse(Integer yMaxValue, Integer xMaxValue, ArrayList<Tondeuse> tondeusesArray){
        Integer xCoord = getRandomNumberUsingNextInt(1, xMaxValue + 1);
        Integer yCoord = getRandomNumberUsingNextInt(1, yMaxValue + 1);
        HashMap<String, Integer> coords = new HashMap<>();
        coords.put("X", xCoord);
        coords.put("Y", yCoord);
        return coords;
    }
}
