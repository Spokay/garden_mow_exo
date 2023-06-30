package manager;

import builder.PartieBuilder;
import model.*;
import model.Obstacles.Tondeuse;
import util.GardenUtils;
import view.JardinStringGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class PartieManager {
    public Partie currentGame;

    public void startGame(){
        this.currentGame = PartieBuilder.build();

        while(!this.currentGame.isFinished){
            this.nextTurn();
        }
    }

    private void nextTurn() {
        // move tondeuses in the direction of the nearest CaseHerbe
        this.moveTondeusesToNearestCaseHerbe();

        // check if the game is finished
        if (this.checkPartieFinished()){
            this.currentGame.setFinished(true);
            System.out.println(this.currentGame.isFinished);
        }
        // add the garden to the turn storage
        Jardin currentJardin = new Jardin(this.currentGame.getCases());
        this.currentGame.getTurnsGardens().add(currentJardin);
        System.out.println(JardinStringGenerator.generateJardinString(currentJardin));
    }


    public void moveTondeusesToNearestCaseHerbe() {
        this.currentGame.getTondeuses().forEach(tondeuse -> {
            Case nextCase = tondeuse.searchForNearestCaseHerbe(this.currentGame.getCases(), tondeuse);
            System.out.println(nextCase.getCoords());
            HashMap<String, Integer> tondeuseNewCoords = getTondeuseNextCoords(tondeuse, nextCase);
            tondeuse.setCoords(tondeuseNewCoords);
            this.currentGame.getCases()[tondeuseNewCoords.get("Y")][tondeuseNewCoords.get("X")] = new CaseOccupee(tondeuseNewCoords, tondeuse);
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
        this.currentGame.getCases()[previousCoords.get("Y")][previousCoords.get("X")] = new CaseTondue(previousCoords);
    }

    public boolean checkPartieFinished(){
        ArrayList<Case> herbeCases = GardenUtils.getAllCaseByType(CaseTypes.CASE_HERBE, this.currentGame.getCases());
        return herbeCases.isEmpty();
    }


}
