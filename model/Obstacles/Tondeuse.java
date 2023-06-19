package model.Obstacles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tondeuse implements Obstacle{
    Integer tondeuseNumber;
    int[][] coords;

    public Tondeuse(Integer tondeuseNumber, int[][] coords) {
        this.tondeuseNumber = tondeuseNumber;
        this.coords = coords;
    }

    public Integer getTondeuseNumber() {
        return tondeuseNumber;
    }

    public void setTondeuseNumber(Integer tondeuseNumber) {
        this.tondeuseNumber = tondeuseNumber;
    }

    public int[][] getCoords() {
        return coords;
    }

    public void setCoords(int[][] coords) {
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
}
