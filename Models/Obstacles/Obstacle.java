package Models.Obstacles;

import java.util.ArrayList;
import java.util.HashMap;

public interface Obstacle {
    ArrayList<HashMap<String, Integer>> coords = new ArrayList<>();

    ArrayList<String> getObstacleAppearance();
    ArrayList<HashMap<String, Integer>> getCoords();
}
