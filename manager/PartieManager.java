package manager;

import builder.CaseBuilder;
import builder.PartieBuilder;
import configuration.GardenMowConfiguration;
import model.*;
import model.Obstacles.Tondeuse;
import util.GardenUtils;
import view.JardinStringGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class PartieManager {
    public Partie currentGame;

    public void startGame(){
        // build the game
        this.currentGame = PartieBuilder.build();

        // move the tondeuses until the game is finished (no CaseHerbeRemaining) or the max number of turn has been reached
        while(!this.currentGame.isFinished || this.currentGame.currentTurn >= GardenMowConfiguration.MAX_TURNS){
            this.nextTurn();
            this.currentGame.currentTurn += 1;
        }
        // print all the gardens turn at the end of the game
        this.currentGame.getTurnsGardens().forEach(garden -> System.out.println(JardinStringGenerator.generateJardinString(garden)));
    }

    private void nextTurn() {

        // make a copy of the case at this turn and build a new Jardin
        Case[][] turnCases = CaseBuilder.buildCopyOfCases(this.moveTondeusesToNearestCaseHerbe());
        Jardin turnGarden = new Jardin(turnCases);

        // add the Jardin to the array of turns
        this.currentGame.getTurnsGardens().add(this.currentGame.currentTurn, turnGarden);

        // check if the game is finished
    }

    public Case[][] moveTondeusesToNearestCaseHerbe() {
        // loop over every tondeuses
        this.currentGame.getTondeuses()
            .stream()
            .takeWhile(tondeuse -> !this.checkPartieFinished())
            .forEach(tondeuse -> {
                // search for the nearest CaseHerbe
                Case nearestCaseHerbe = tondeuse.searchForNearestCaseHerbe(this.currentGame.getCases(), tondeuse);

                // replace the case where the tondeuse was located before by a CaseTondue
                replacePreviousCaseByTondue(tondeuse.getCoords());

                // new coords for the tondeuse to move towards the nearest case
                HashMap<String, Integer> tondeuseNewCoords = getTondeuseNextCoords(tondeuse, nearestCaseHerbe);

                // change the tondeuse coords to the fresh coords retrieved
                tondeuse.setCoords(tondeuseNewCoords);

                // set the new case to type CaseOccupee
                this.currentGame.getCases()[tondeuseNewCoords.get("Y")][tondeuseNewCoords.get("X")] = new CaseOccupee(tondeuseNewCoords, tondeuse);

                // set the finished boolean to true if the game is done
                if (this.checkPartieFinished()){
                    this.currentGame.setFinished(true);
                }
        });
        return this.currentGame.getCases();
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
        boolean isCollapsing = this.currentGame.getCases()[yNextCoord][xNextCoord].getCaseType().equals(CaseTypes.CASE_OCCUPEE);

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
        this.currentGame.getCases()[previousCoords.get("Y")][previousCoords.get("X")] = new CaseTondue(previousCoords);
    }

    public boolean checkPartieFinished(){
        // check if the game if finished (if no CaseHerbe are in the garden)
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseTypes.CASE_HERBE, this.currentGame.getCases());
        return herbeCases.isEmpty();
    }


}
