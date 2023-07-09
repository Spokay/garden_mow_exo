package model.Case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CaseTondue extends Case{

    public CaseTondue(HashMap<String, Integer> coords) {
        super(coords);
        this.caseType = CaseStatus.CASE_TONDUE;
        ArrayList<String> appearances = new ArrayList<>(
                Arrays.asList(
                        "        ",
                        "        ",
                        "        "
                )
        );
        this.setCaseAppearance(appearances);
    }
}
