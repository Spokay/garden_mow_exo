import Models.*;
import Views.JardinStringGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GardenMowApplication {
    public static void main(String[] args) {
        // run the application

        // tests
        HashMap<Integer, HashMap<Integer, Case>> cases = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            HashMap<Integer, Case> row = new HashMap<>();
            for (int j = 1; j < 5; j++) {
                if (j % 2 == 0){
                    row.put(j, new CaseHerbe(i, j));
                }else {
                    Tondeuse tondeuse = new Tondeuse();
                    tondeuse.setTondeuseNumber(1);
                    ArrayList<HashMap<String, Integer>> coords = new ArrayList<>();
                    HashMap<String, Integer> coordsMap = new HashMap<>();
                    coordsMap.put("Y", i);
                    coordsMap.put("X", j);
                    coords.add(coordsMap);
                    tondeuse.setCoords(coords);
                    row.put(j, new CaseOccupee(i, j, tondeuse));
                }

            }
            cases.put(i, row);
        }


        Jardin jardinTest = new Jardin(cases);

        System.out.println(JardinStringGenerator.generateJardinString(jardinTest));
    }
}
