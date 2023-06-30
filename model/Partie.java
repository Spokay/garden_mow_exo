package model;

import model.Obstacles.Obstacle;
import model.Obstacles.ObstaclesTypes;
import model.Obstacles.Tondeuse;

import java.util.ArrayList;
import java.util.HashMap;

public class Partie {
    public boolean isFinished;
    private Integer xLength;
    private Integer yLength;
    private Case[][] cases;
    private ArrayList<Tondeuse> tondeuses;
    private HashMap<ObstaclesTypes, ArrayList<Obstacle>> obstacles;

    private ArrayList<Jardin> turnsGardens;
    public Integer currentTurn = 1;

    public Partie(Integer xLength, Integer yLength, ArrayList<Tondeuse> tondeuses, Case[][] cases,ArrayList<Jardin> turnsGarden) {
        this.xLength = xLength;
        this.yLength = yLength;
        this.tondeuses = tondeuses;
        this.cases = cases;
        this.turnsGardens = turnsGarden;
    }

    public Case getCaseByCoords(Integer xValue, Integer yValue) {
        return this.cases[yValue][xValue];
    }

    public Integer getxLength() {
        return xLength;
    }

    public void setxLength(Integer xLength) {
        this.xLength = xLength;
    }

    public Integer getyLength() {
        return yLength;
    }

    public void setyLength(Integer yLength) {
        this.yLength = yLength;
    }

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public ArrayList<Tondeuse> getTondeuses() {
        return tondeuses;
    }

    public void setTondeuses(ArrayList<Tondeuse> tondeuses) {
        this.tondeuses = tondeuses;
    }

    public HashMap<ObstaclesTypes, ArrayList<Obstacle>> getObstacles() {
        return obstacles;
    }

    public void setObstacles(HashMap<ObstaclesTypes, ArrayList<Obstacle>> obstacles) {
        this.obstacles = obstacles;
    }

    public ArrayList<Jardin> getTurnsGardens() {
        return turnsGardens;
    }

    public void setTurnsGardens(ArrayList<Jardin> turnsGardens) {
        this.turnsGardens = turnsGardens;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

}
