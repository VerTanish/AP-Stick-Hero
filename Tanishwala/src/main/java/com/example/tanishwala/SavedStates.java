package com.example.tanishwala;

public class SavedStates {
    private int score;
    private double platformwidth;

    public int getCherryscore() {
        return cherryscore;
    }

    public void setCherryscore(int cherryscore) {
        this.cherryscore = cherryscore;
    }

    private double obstaclewidth;

    private double platformlayoutX;
    private double obstaclelayoutX;

    private double pillarheight;

    private int cherryscore;

    public SavedStates(int score, double platformwidth, double obstaclewidth, double platformlayoutX, double obstaclelayoutX, double pillarheight) {
        this.score = score;
        this.platformwidth = platformwidth;
        this.obstaclewidth = obstaclewidth;
        this.platformlayoutX = platformlayoutX;
        this.obstaclelayoutX = obstaclelayoutX;
        this.pillarheight = pillarheight;
    }

    public SavedStates(int score,int cherryscore){
        this.cherryscore = cherryscore;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "SavedStates{" +
                "score=" + score +
                ", cherryscore=" + cherryscore +
                '}';
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPlatformwidth() {
        return platformwidth;
    }

    public void setPlatformwidth(double platformwidth) {
        this.platformwidth = platformwidth;
    }

    public double getObstaclewidth() {
        return obstaclewidth;
    }

    public void setObstaclewidth(double obstaclewidth) {
        this.obstaclewidth = obstaclewidth;
    }

    public double getPlatformlayoutX() {
        return platformlayoutX;
    }

    public void setPlatformlayoutX(double platformlayoutX) {
        this.platformlayoutX = platformlayoutX;
    }

    public double getObstaclelayoutX() {
        return obstaclelayoutX;
    }

    public void setObstaclelayoutX(double obstaclelayoutX) {
        this.obstaclelayoutX = obstaclelayoutX;
    }

    public double getPillarheight() {
        return pillarheight;
    }

    public void setPillarheight(double pillarheight) {
        this.pillarheight = pillarheight;
    }
}

