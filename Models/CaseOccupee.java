package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class CaseOccupee extends Case{

    Tondeuse tondeuse;

    public CaseOccupee(Integer yValue ,Integer xValue, Tondeuse tondeuse) {
        super(yValue, xValue);
        this.caseType = CaseTypes.CASE_OCCUPEE;
        this.setTondeuse(tondeuse);
        ArrayList<String> appearances = new ArrayList<>(
                Arrays.asList(
                        "        ",
                        "   T".concat(String.valueOf(this.getTondeuse().getTondeuseNumber())).concat("   "),
                        "        "
                )
        );
        this.setCaseAppearance(appearances);
    }

    public CaseHerbe getNearCaseHerbe(){
        return null;
    }

    public Tondeuse getTondeuse() {
        return tondeuse;
    }

    public void setTondeuse(Tondeuse tondeuse) {
        this.tondeuse = tondeuse;
    }
}
