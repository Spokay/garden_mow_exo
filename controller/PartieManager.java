package controller;

import builder.CaseBuilder;
import builder.PartieBuilder;
import configuration.GardenMowConfiguration;
import model.*;
import model.Obstacles.Tondeuse;
import util.GardenUtils;
import view.JardinStringGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class PartieManager {
    public Partie currentGame;

    public void startGame(){
        // build the game
        this.currentGame = PartieBuilder.build();

        // move the tondeuses until the game is finished (no CaseHerbeRemaining) or the max number of turn has been reached
        while(!this.currentGame.isFinished || this.currentGame.currentTurn >= GardenMowConfiguration.MAX_TURNS){
            this.nextTurn();
            this.currentGame.currentTurn += 1;
            System.out.println(this.currentGame.isFinished);
        }
        // print all the gardens turn at the end of the game
        this.currentGame.getTurnsGardens().forEach(garden -> System.out.println(JardinStringGenerator.generateJardinString(garden)));
    }

    private void nextTurn() {
        System.out.println("newTurn");
        System.out.println(Arrays.deepToString(this.currentGame.getCases()));

        // make a copy of the case at this turn and build a new Jardin
        Case[][] turnCases = CaseBuilder.buildCopyOfCases(this.moveTondeusesToNearestCaseHerbe());
        Jardin turnGarden = new Jardin(turnCases);

        // add the Jardin to the array of turns
        this.currentGame.getTurnsGardens().add(this.currentGame.currentTurn, turnGarden);

        // check if the game is finished
        if (this.checkPartieFinished()){
            this.currentGame.setFinished(true);
        }
    }

    public Case[][] moveTondeusesToNearestCaseHerbe() {
        // loop over every tondeuses
        this.currentGame.getTondeuses().forEach(tondeuse -> {
            Case nearestCaseHerbe = tondeuse.searchForNearestCaseHerbe(this.currentGame.getCases(), tondeuse);
            this.replacePreviousCaseByTondue(tondeuse.getCoords());
            HashMap<String, Integer> tondeuseNewCoords = getTondeuseNextCoords(tondeuse, nearestCaseHerbe);
            tondeuse.setCoords(tondeuseNewCoords);
            this.currentGame.getCases()[tondeuseNewCoords.get("Y")][tondeuseNewCoords.get("X")] = new CaseOccupee(tondeuseNewCoords, tondeuse);
        });

        return this.currentGame.getCases();
    }

    private HashMap<String, Integer> getTondeuseNextCoords(Tondeuse tondeuse, Case nextCase) {
        System.out.println(nextCase.getCoords());
        int yDiffToCase = GardenUtils.getTondeuseYDiffToCase(tondeuse, nextCase);
        int xDiffToCase = GardenUtils.getTondeuseXDiffToCase(tondeuse, nextCase);
        System.out.println(tondeuse.getCoords());
        System.out.println(xDiffToCase + " | " + yDiffToCase);
        boolean isRight = xDiffToCase > 0;
        boolean isLeft = xDiffToCase < 0;
        boolean isTop = yDiffToCase < 0;
        boolean isBottom = yDiffToCase > 0;


        Integer xNextCoord;
        Integer yNextCoord;

        if (isRight){
            System.out.println(tondeuse.getCoords().get("X") + 1);
            xNextCoord = tondeuse.getCoords().get("X") + 1;
        } else if (isLeft) {
            System.out.println(tondeuse.getCoords().get("X") - 1);
            xNextCoord = tondeuse.getCoords().get("X") - 1;
        }else{
            System.out.println(tondeuse.getCoords().get("X"));
            xNextCoord = tondeuse.getCoords().get("X");
        }
        if (isTop){
            System.out.println(tondeuse.getCoords().get("Y") - 1);
            yNextCoord = (tondeuse.getCoords().get("Y") - 1);
        } else if (isBottom) {
            System.out.println(tondeuse.getCoords().get("Y") + 1);
            yNextCoord = (tondeuse.getCoords().get("Y") + 1);
        }else{
            System.out.println(tondeuse.getCoords().get("Y"));
            yNextCoord = tondeuse.getCoords().get("Y");
        }

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
        this.currentGame.getCases()[previousCoords.get("Y")][previousCoords.get("X")] = new CaseTondue(previousCoords);
    }

    public boolean checkPartieFinished(){
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseTypes.CASE_HERBE, this.currentGame.getCases());
        return herbeCases.isEmpty();
    }


}
