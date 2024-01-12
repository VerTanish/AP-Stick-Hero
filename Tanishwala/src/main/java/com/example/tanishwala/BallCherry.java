package com.example.tanishwala;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.shape.Circle;

public class BallCherry extends Circle{

    public BallCherry(double layx) {
        // Set the properties of the Obstacle
        setLayoutX(layx);
        setLayoutY(455);
        setRadius(10);
        setFill(Color.valueOf("#fffa1f")); // Fill color
        setStroke(Color.TRANSPARENT); // Stroke color
        setStrokeType(StrokeType.INSIDE);

    }

}
