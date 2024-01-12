package com.example.tanishwala;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public abstract class Pillar extends Rectangle {
    public Pillar(double width, double height, double layoutX, double layoutY) {
        // Set the properties of the Obstacle
        setWidth(width);
        setHeight(height);
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setFill(Color.valueOf("#5c8374")); // Fill color
        setArcWidth(5.0);
        setArcHeight(5.0);
        setStroke(Color.TRANSPARENT); // Stroke color
        setStrokeType(StrokeType.INSIDE);

        // Additional customization if needed
    }

    public double collision(Line l, Rectangle r) {
        Rectangle _platform = new Rectangle();
//        double lower_bound = r.getLayoutX() - player.getLayoutX() - player.getWidth() ;
        double lower_bound = l.getEndX();
        double upper_bound = r.getLayoutX();
        double distance = upper_bound - lower_bound;
        return distance;
    }
}
