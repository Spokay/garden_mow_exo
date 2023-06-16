package Utils;

import Models.Obstacles.Obstacle;
import Models.Obstacles.ObstaclesTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
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
        // keep getting new coords until a valid one is available
        while(isCrossingObstacle(xCoord, yCoord, obstaclesArray)){
            xCoord = getRandomNumberUsingNextInt(1, xMaxValue + 1);
            yCoord = getRandomNumberUsingNextInt(1, yMaxValue + 1);
        }
        // fill a Hashmap with the final coords
        HashMap<String, Integer> coords = new HashMap<>();
        coords.put("X", xCoord);
        coords.put("Y", yCoord);
        return coords;
    }

    public static boolean isCrossingObstacle(Integer xCoord, Integer yCoord, HashMap<ObstaclesTypes, ArrayList<? extends Obstacle>> obstaclesArray){
        System.out.println("ok");
        // initialize a boolean
        AtomicBoolean isCrossingBool = new AtomicBoolean(false);
        // loop through all obstacles to check is the given coordinate is crossing an obstacle

        // todo: STILL NOT WORKING
        obstaclesArray.forEach(
            (obstacleType, obstacleTypeArray) -> obstacleTypeArray.forEach(
                    obstacle -> obstacle.getCoords().forEach(
                            caseOccupied -> {
                                System.out.println("ok");
                                if (Objects.equals(caseOccupied.get("X"), xCoord) && Objects.equals(caseOccupied.get("Y"), yCoord)){
                                    System.out.println(xCoord + ":" + yCoord);
                                    isCrossingBool.set(true);
                                } else {
                                    System.out.println("not crossing");
                                }
                            }
                    )
            )
        );
        return isCrossingBool.get();
    }
}
