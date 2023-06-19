package controller;

import model.Case;

import java.util.HashMap;

public class CaseController {
    HashMap<Integer, HashMap<Integer, Case>> cases;
    public Case getCaseByCoords(Integer xValue, Integer yValue) {
        return this.cases.get(yValue).get(xValue);
    }
}
