package Utils;

import Models.Obstacles.Obstacle;
import Models.Obstacles.ObstaclesTypes;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GardenUtils {
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static HashMap<String, Integer> getRandomCoordsNotCrossingObstacle(Integer yMaxValue, Integer xMaxValue, HashMap<ObstaclesTypes, ArrayList<? extends Obstacle>> obstaclesArray){
        // get randoms coords
        int xCoord = getRandomNumberUsingNextInt(1, xMaxValue + 1);
        int yCoord = getRandomNumberUsingNextInt(1, yMaxValue + 1);
        HashMap<String, Integer> coords = new HashMap<>();
        coords.put("X", xCoord);
        coords.put("Y", yCoord);
        // keep getting new coords until a valid one is available
        while(isCrossingObstacle(coords, obstaclesArray)){
            coords.replace("X", getRandomNumberUsingNextInt(1, xMaxValue + 1));
            coords.replace("Y", getRandomNumberUsingNextInt(1, yMaxValue + 1));
        }

        return coords;
    }

    public static boolean isCrossingObstacle(HashMap<String, Integer> coords, HashMap<ObstaclesTypes, ArrayList<? extends Obstacle>> obstaclesArray){

        System.out.println(obstaclesArray);
        // initialize a boolean
        AtomicBoolean isCrossingBool = new AtomicBoolean();
        // loop through all obstacles to check is the given coordinate is crossing an obstacle

        // todo: STILL NOT WORKING
        for (Map.Entry<ObstaclesTypes, ArrayList<? extends Obstacle>> entry : obstaclesArray.entrySet()) {
            ObstaclesTypes obstaclesTypes = entry.getKey();
            ArrayList<? extends Obstacle> obstacles = entry.getValue();
            isCrossingBool.set(obstacles.stream()
                    .anyMatch(obstacle -> obstacle.getCoords()
                            .stream().filter(caseOccupied -> caseOccupied.equals(coords)).isParallel()
                    )
            );
            System.out.println(isCrossingBool.get());
            if (isCrossingBool.get()){
                return isCrossingBool.get();
            }
        }
        return isCrossingBool.get();
    }
}
