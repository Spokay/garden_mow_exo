package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class CaseHerbe extends Case{

    public CaseHerbe(Integer yValue, Integer xValue) {
        super(yValue, xValue);
        this.caseType = CaseTypes.CASE_HERBE;
        ArrayList<String> appearances = new ArrayList<>(
                Arrays.asList(
                        "XXXXXXXX",
                        "XXXXXXXX",
                        "XXXXXXXX"
                )
        );
        this.setCaseAppearance(appearances);
    }
}
