package util;

import model.Case;
import model.Obstacles.Obstacle;
import model.Obstacles.ObstaclesTypes;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GardenUtils {
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static int[][] getRandomCoordsNotCrossingObstacle(Integer yMaxValue, Integer xMaxValue, HashMap<Integer, HashMap<Integer, Case>> obstaclesArray){
        // get randoms coords
        int xCoord = getRandomNumberUsingNextInt(1, xMaxValue + 1);
        int yCoord = getRandomNumberUsingNextInt(1, yMaxValue + 1);

        coords[yCoord][xCoord] = xCoord;
        // keep getting new coords until a valid one is available
        while(isCrossingObstacle(coords, obstaclesArray)){
            coords[yCoord] = getRandomNumberUsingNextInt(1, yMaxValue + 1);
//            coords.replace("Y", getRandomNumberUsingNextInt(1, yMaxValue + 1));
        }

        return coords;
    }

    public static boolean isCrossingObstacle(int[][] coords, HashMap<Integer, HashMap<Integer, Case>> obstaclesArray){

        Case[][] caseJardin = new Case[2][2];
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
