package model.Obstacles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tondeuse implements Obstacle{
    Integer tondeuseNumber;
    ArrayList<HashMap<String, Integer>> coords  = new ArrayList<>();

    public Tondeuse(Integer tondeuseNumber, HashMap<String, Integer> coordsOrigin) {
        this.tondeuseNumber = tondeuseNumber;
        this.coords.add(coordsOrigin);
    }

    public Integer getTondeuseNumber() {
        return tondeuseNumber;
    }

    public void setTondeuseNumber(Integer tondeuseNumber) {
        this.tondeuseNumber = tondeuseNumber;
    }

    public ArrayList<HashMap<String, Integer>> getCoords() {
        return coords;
    }

    public void setCoords(ArrayList<HashMap<String, Integer>> coords) {
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
