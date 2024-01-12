package com.example.tanishwala;


import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Platform extends Pillar {

    public Platform(double width, double height, double layoutX, double layoutY){
        super(width, height, layoutX, layoutY);
        // above we create an obstacle which inherits from PillarObstacle
    }

    public double collision(Line l, Rectangle r){
        Rectangle _platform = new Rectangle();
//        double lower_bound = r.getLayoutX() - player.getLayoutX() - player.getWidth() ;
        double lower_bound = l.getEndX();
        double upper_bound = r.getLayoutX();
        double distance = upper_bound - lower_bound;
        return distance;
    }
}