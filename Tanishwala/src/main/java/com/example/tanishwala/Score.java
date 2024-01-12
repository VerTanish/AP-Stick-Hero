package com.example.tanishwala;

public class Score {
    private int score;
    Score(int _score){
        score = _score;
    }
    public void increasebyOne(){
        score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

