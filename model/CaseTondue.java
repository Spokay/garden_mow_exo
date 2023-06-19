package model;

import java.util.ArrayList;
import java.util.Arrays;

public class CaseTondue extends Case{

    public CaseTondue(Integer yValue, Integer xValue) {
        super(yValue, xValue);
        this.caseType = CaseTypes.CASE_TONDUE;
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
