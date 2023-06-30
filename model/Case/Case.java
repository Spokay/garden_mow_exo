package model.Case;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Case{
    CaseTypes caseType;
    HashMap<String, Integer> coords;

    ArrayList<String> caseAppearance;

    public Case(HashMap<String, Integer> coords) {
        this.setCoords(coords);
    }

    public HashMap<String, Integer> getCoords() {
        return this.coords;
    }

    public void setCoords(HashMap<String, Integer> coords) {
        this.coords = coords;
    }

    public ArrayList<String> getCaseAppearance() {
        return this.caseAppearance;
    }

    public void setCaseAppearance(ArrayList<String> caseAppearance) {
        this.caseAppearance = caseAppearance;
    }

    public CaseTypes getCaseType() {
        return caseType;
    }

    public void setCaseType(CaseTypes caseType) {
        this.caseType = caseType;
    }
}
