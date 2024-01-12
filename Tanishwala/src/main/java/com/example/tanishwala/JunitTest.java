package com.example.tanishwala;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JunitTest {
    @Test
    public void TestGame1(){
        Line l = new Line();
        l.setStartX(0);
        Rectangle r = new Rectangle(0,0);
        r.setLayoutX(250);
        Obstacle res = new Obstacle(0,0,0,0);
        int result = (int)res.collision(l,r);
        assertEquals(250,result);
    }

    @Test
    public void TestGame2(){
        StickClass a1 = StickClass.getInstance();
        StickClass a2 = StickClass.getInstance();
        assertEquals(a1,a2);
    }
}
