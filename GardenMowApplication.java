import controller.PartieController;

public class GardenMowApplication {
    public static void main(String[] args) {
        // run the application

        // tests
        /*HashMap<Integer, HashMap<Integer, Case>> cases = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            HashMap<Integer, Case> row = new HashMap<>();
            for (int j = 1; j < 5; j++) {
                if (j % 2 == 0){
                    row.put(j, new CaseHerbe(i, j));
                }else {
                    ArrayList<HashMap<String, Integer>> coords = new ArrayList<>();
                    HashMap<String, Integer> coordsMap = new HashMap<>();
                    coordsMap.put("Y", i);
                    coordsMap.put("X", j);
                    coords.add(coordsMap);
                    Tondeuse tondeuse = new Tondeuse(1, coords);
                    row.put(j, new CaseOccupee(i, j, tondeuse));
                }

            }
            cases.put(i, row);
        }*/


//        Jardin jardinTest = new Jardin(cases);

//        System.out.println(JardinStringGenerator.generateJardinString(jardinTest));
        PartieController gameController = new PartieController();
        gameController.startGame();
    }
}
