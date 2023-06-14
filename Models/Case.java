package Models;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Case{
    CaseTypes caseType;
    HashMap<String, Integer> coords;

    ArrayList<String> caseAppearance;

    public Case(Integer yValue, Integer xValue) {
        this.coords = new HashMap<>();
        this.setCoords(xValue, yValue);
    }

    public HashMap<String, Integer> getCoords() {
        return this.coords;
    }

    public void setCoords(Integer xValue, Integer yValue) {
        this.coords.put("X", xValue);
        this.coords.put("Y", yValue);
    }

    public ArrayList<String> getCaseAppearance() {
        return this.caseAppearance;
    }

    public void setCaseAppearance(ArrayList<String> caseAppearance) {
        this.caseAppearance = caseAppearance;
    }
}
