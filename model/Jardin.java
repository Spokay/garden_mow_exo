package model;

import model.Case.Case;

public class Jardin {

    private Case[][] cases;

    private int gardenTurn;



    public Jardin(Case[][] cases, int gardenTurn) {
        this.cases = cases;
        this.gardenTurn = gardenTurn;
    }

    public Case[][] getCases() {
        return cases;
    }
    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public int getGardenTurn() {
        return gardenTurn;
    }

    public void setGardenTurn(int gardenTurn) {
        this.gardenTurn = gardenTurn;
    }
}
