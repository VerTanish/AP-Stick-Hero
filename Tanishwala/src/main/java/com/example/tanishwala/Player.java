package com.example.tanishwala;

import javafx.scene.paint.Color;

public class Player {
    private double width;
    private double length;
    private Color color;

    Player(){

    }

    public Color getColor() {
        return color;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
