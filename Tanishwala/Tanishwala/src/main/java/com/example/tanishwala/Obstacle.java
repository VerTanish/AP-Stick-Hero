package com.example.tanishwala;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Obstacle extends Rectangle{

    public Obstacle(double width, double height, double layoutX, double layoutY) {
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

}
