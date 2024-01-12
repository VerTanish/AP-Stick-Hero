package com.example.tanishwala;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.input.*;

public class StartPageController {

    boolean isStickFallen = false;

    public void switchtomaingame(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("GAME.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();

        GamePageController gamePageController = fxmlLoader.getController();
        //gamePageController.generatenewobstacle();
        gamePageController.initialisestick();



        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE){
                    if (!isStickFallen) {
                        System.out.println("key pressed");
                        gamePageController.growstick();
                    }
                }
                if (event.getCode() == KeyCode.DOWN){
                    gamePageController.flipvertical();
                }
                if (event.getCode() == KeyCode.G){

                    System.out.println(gamePageController.getPlatform().getLayoutX());
                    System.out.println(gamePageController.getPlatform().getWidth());
                    System.out.println(gamePageController.getObstacle().getLayoutX());
                    System.out.println(gamePageController.getObstacle().getWidth());
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE){
                    if (!isStickFallen) {
                        System.out.println("krey released");
                        isStickFallen = true;
                        System.out.println("below are P1 and P2");
                        System.out.println(gamePageController.getPlatform().getWidth());
                        System.out.println(gamePageController.getObstacle().getWidth());
                        gamePageController.restofthecode();
                        isStickFallen = false;

                    }
                }
            }
        });

    }

    public void switchtosavedstate(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("SAVEDSTATE.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();
    }

    public void volumeswitch(){

    }



}