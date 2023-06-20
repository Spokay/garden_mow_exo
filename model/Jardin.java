package model;

import java.util.HashMap;

public class Jardin {

    Case[][] cases;
    public Jardin(Case[][] cases) {
        this.cases = cases;
    }

    public Case[][] getCases() {
        return cases;
    }
}
