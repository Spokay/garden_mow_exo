package model;

import java.util.HashMap;

public class Jardin {

    HashMap<Integer, HashMap<Integer, Case>> cases;
    public Jardin(HashMap<Integer, HashMap<Integer, Case>> cases) {
        this.cases = cases;
    }

    public HashMap<Integer, HashMap<Integer, Case>> getCases() {
        return cases;
    }
}
