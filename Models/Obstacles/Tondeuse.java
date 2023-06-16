package Models.Obstacles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tondeuse implements Obstacle{
    Integer tondeuseNumber;
    ArrayList<HashMap<String, Integer>> coords;

    public Tondeuse(Integer tondeuseNumber, ArrayList<HashMap<String, Integer>> coords) {
        this.tondeuseNumber = tondeuseNumber;
        this.coords = coords;
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
