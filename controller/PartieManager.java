package controller;

import builder.PartieBuilder;
import model.Case;
import model.CaseHerbe;
import model.CaseTypes;
import model.Obstacles.Tondeuse;
import model.Partie;
import util.GardenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PartieManager {
    public Partie currentGame;

    public void startGame(){
        this.currentGame = PartieBuilder.build();
        System.out.println(this.currentGame.isFinished);
        while(!this.currentGame.isFinished){
            this.nextTurn();
        }
    }

    private void nextTurn() {
        this.moveTondeusesToNearestCaseHerbe();

        if (this.checkPartieFinished()){
            this.currentGame.setFinished(true);
        }
    }

    public void moveTondeusesToNearestCaseHerbe() {
        this.currentGame.getTondeuses().forEach(tondeuse -> {
            Case nextCase = tondeuse.searchForNearestCaseHerbe(this.currentGame.getCases(), tondeuse);
            HashMap<String, Integer> tondeuseNewCoords = getTondeuseNextCoords(tondeuse, nextCase);
            tondeuse.setCoords(tondeuseNewCoords);
            this.replacePreviousCaseByTondue(tondeuse.getCoords());
        });
    }

    private HashMap<String, Integer> getTondeuseNextCoords(Tondeuse tondeuse, Case nextCase) {

        int yDiffToCase = GardenUtils.getTondeuseYDiffToCase(tondeuse, nextCase);
        int xDiffToCase = GardenUtils.getTondeuseXDiffToCase(tondeuse, nextCase);
        boolean isRight = xDiffToCase > 0;
        boolean isLeft = xDiffToCase < 0;
        boolean isTop = yDiffToCase > 0;
        boolean isBottom = yDiffToCase < 0;

        int xNextCoord = isRight ? tondeuse.getCoords().get("X") + 1 : isLeft ? tondeuse.getCoords().get("X") - 1 : tondeuse.getCoords().get("X");
        int yNextCoord = isTop ? tondeuse.getCoords().get("Y") + 1 : isBottom ? tondeuse.getCoords().get("Y") - 1 : tondeuse.getCoords().get("Y");

        // check if the tondeuse can go on the case or is blocked
        boolean isCollapsing = this.currentGame.getCases()[yNextCoord][xNextCoord].getCaseType().equals(CaseTypes.CASE_OCCUPEE);

        HashMap<String, Integer> nextCoords = new HashMap<>();
        if (isCollapsing){
            nextCoords.put("X", tondeuse.getCoords().get("X"));
            nextCoords.put("Y", tondeuse.getCoords().get("Y"));
        }else{
            nextCoords.put("X", xNextCoord);
            nextCoords.put("Y", yNextCoord);
        }

        return nextCoords;
    }


    private void replacePreviousCaseByTondue(HashMap<String, Integer> previousCoords) {
        this.currentGame.getCases()[previousCoords.get("Y")][previousCoords.get("X")] = new CaseHerbe(previousCoords);
    }

    public boolean checkPartieFinished(){
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseTypes.CASE_HERBE, this.currentGame.getCases());
        return herbeCases.isEmpty();
    }


}
