package model;

import model.Obstacles.Obstacle;

import java.util.ArrayList;
import java.util.HashMap;

public class CaseOccupee extends Case{

    Obstacle obstacle;

    public CaseOccupee(HashMap<String, Integer> coords, Obstacle obstacle) {
        super(coords);
        this.caseType = CaseTypes.CASE_OCCUPEE;
        this.setObstacle(obstacle);
        ArrayList<String> appearances = this.obstacle.getObstacleAppearance();
        this.setCaseAppearance(appearances);
    }

    public CaseHerbe getNearCaseHerbe(){
        return null;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}
