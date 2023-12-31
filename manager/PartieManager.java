package manager;

import builder.CaseBuilder;
import builder.PartieBuilder;
import configuration.GardenMowConfiguration;
import model.*;
import model.Case.Case;
import model.Case.CaseOccupee;
import model.Case.CaseTondue;
import model.Case.CaseStatus;
import model.Obstacle.Tondeuse;
import util.GardenUtils;
import view.GardenPrinter;

import java.util.ArrayList;
import java.util.HashMap;

public class PartieManager {
    public Partie currentGame;

    public static Case[][] currentGrid;

    public void startGame(){
        // build the game
        currentGame = PartieBuilder.build();

        PartieManager.currentGrid = currentGame.getCases();
        // move the tondeuses until the game is finished (no CaseHerbeRemaining) or the max number of turn has been reached
        while(isNotFinishedOrMaxTurnReached()){
            nextTurn();
            currentGame.currentTurn += 1;
        }

        // print a report at the end of the game
        GardenPrinter.printGameReport(currentGame.getTurnsGardens(), currentGame.isFinished);
    }



    private void nextTurn() {
        // make a copy of the case at this turn and build a new Jardin
        Case[][] turnCases = CaseBuilder.buildCopyOfCases(moveTondeusesToNearestCaseHerbe());
        Jardin turnGarden = new Jardin(turnCases, currentGame.currentTurn);

        // add the Jardin to the array of turns
        currentGame.getTurnsGardens().add(currentGame.currentTurn, turnGarden);
    }

    public Case[][] moveTondeusesToNearestCaseHerbe() {
        // loop over every tondeuses
        currentGame.getTondeuses()
            .stream()
            .takeWhile(tondeuse -> !checkPartieFinished())
            .forEach(this::moveTondeuseToNearestCaseHerbe);
        return currentGame.getCases();
    }

    private HashMap<String, Integer> getTondeuseNextCoords(Tondeuse tondeuse, Case nearestCase) {
        // get the respective X and Y difference between the tondeuse and the nearest case
        int yDiffToCase = GardenUtils.getTondeuseYDiffToCase(tondeuse, nearestCase);
        int xDiffToCase = GardenUtils.getTondeuseXDiffToCase(tondeuse, nearestCase);

        // booleans to check in which direction the nearest case is located from the tondeuse POV
        boolean isRight = xDiffToCase > 0;
        boolean isLeft = xDiffToCase < 0;
        boolean isTop = yDiffToCase < 0;
        boolean isBottom = yDiffToCase > 0;

        // move the tondeuse 1 case closer to the nearest case (diagonals enabled), if no condition is met (no case remaining) just set the next coords to the same as before
         int xNextCoord = isRight ? tondeuse.getCoords().get("X") + 1 : isLeft ? tondeuse.getCoords().get("X") - 1 : tondeuse.getCoords().get("X");
        int yNextCoord = isTop ? tondeuse.getCoords().get("Y") - 1 : isBottom ? tondeuse.getCoords().get("Y") + 1 : tondeuse.getCoords().get("Y");

        // check if the tondeuse can go on the case or is blocked
        boolean isCollapsing = currentGame.getCases()[yNextCoord][xNextCoord].getCaseType().equals(CaseStatus.CASE_OCCUPEE);

        HashMap<String, Integer> nextCoords = new HashMap<>();

        // if the next case is not accessible stay still
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
        // replace the case where the tondeuse passed by a CaseTondue
        currentGame.getCases()[previousCoords.get("Y")][previousCoords.get("X")] = new CaseTondue(previousCoords);
    }

    public boolean checkPartieFinished(){
        // check if the game if finished (if no CaseHerbe are in the garden)
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseStatus.CASE_HERBE, currentGame.getCases());
        return herbeCases.isEmpty();
    }

    public void moveTondeuseToNearestCaseHerbe(Tondeuse tondeuse){
        Case nearestCaseHerbe = tondeuse.searchForNearestCaseHerbe(currentGame.getCases(), tondeuse);

        // replace the case where the tondeuse was located before by a CaseTondue
        replacePreviousCaseByTondue(tondeuse.getCoords());

        // new coords for the tondeuse to move towards the nearest case
        HashMap<String, Integer> tondeuseNewCoords = getTondeuseNextCoords(tondeuse, nearestCaseHerbe);

        // change the tondeuse coords to the fresh coords retrieved
        tondeuse.setCoords(tondeuseNewCoords);

        // set the new case to type CaseOccupee
        currentGame.getCases()[tondeuseNewCoords.get("Y")][tondeuseNewCoords.get("X")] = new CaseOccupee(tondeuseNewCoords, tondeuse);

        // set the finished boolean to true if the game is done
        if (checkPartieFinished()){
            currentGame.setFinished(true);
        }
    }

    private boolean isNotFinishedOrMaxTurnReached(){
        return !checkPartieFinished() || currentGame.currentTurn >= GardenMowConfiguration.MAX_TURNS;
    }

}
