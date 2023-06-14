package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Tondeuse {
    Integer tondeuseNumber;
    ArrayList<HashMap<String, Integer>> coords;

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
}
