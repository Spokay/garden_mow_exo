package model.Case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CaseHerbe extends Case{

    public CaseHerbe(HashMap<String, Integer> coords) {
        super(coords);
        this.caseType = CaseStatus.CASE_HERBE;
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
