package com.example.tanishwala;

import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class StickClass extends Line { // Singleton design pattern implemented to ensure a single stick

    private static volatile StickClass instance; // declaring volatile so that double stick is not created even in mutltthreaded environment

    private StickClass() {
        // Initialization code, if needed // private constructor
    }

    public static StickClass getInstance() { /// getting instance
        if (instance == null) {
            synchronized (StickClass.class) {
                if (instance == null) {
                    instance = new StickClass();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StickClass other = (StickClass) obj;
        return true;
    }

    public boolean collision(Line l, Rectangle r){
        Node player = null;
        Rectangle _platform = new Rectangle();
        System.out.println(player.getLayoutX());
        System.out.println(player.getLayoutX());
//        double lower_bound = r.getLayoutX() - player.getLayoutX() - player.getWidth() ;
        double lower_bound = r.getLayoutX() - (40+ _platform.getWidth());
        double upper_bound = lower_bound + r.getWidth();
        System.out.println(lower_bound);
        System.out.println(upper_bound);
        double distance = 0;
        System.out.println("X"+distance);
        if (lower_bound <= distance && distance <= upper_bound){
            return true;
        }
        return false;
    }
}
