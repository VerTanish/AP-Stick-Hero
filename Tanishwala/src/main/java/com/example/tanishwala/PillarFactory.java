package com.example.tanishwala;

import javafx.scene.shape.Shape;

public class PillarFactory {

    public Shape getPillar(int number){
        if (number == 1){
            return new Obstacle(0,0,0,0);
        }
        else if (number == 2){
            return new Platform(0,0,0,0);
        }
        else{
            return null;
        }
    }
}