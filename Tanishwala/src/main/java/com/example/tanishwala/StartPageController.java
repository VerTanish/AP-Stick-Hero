package com.example.tanishwala;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.input.*;

public class StartPageController {

    @FXML
    public Button REVIVEbutton;
    @FXML
    public Text GreenText;
    @FXML
    public Text RedText;

    public Button getREVIVEbutton() {
        return REVIVEbutton;
    }

    public void setREVIVEbutton(Button REVIVEbutton) {
        this.REVIVEbutton = REVIVEbutton;
    }

    public Text getGreenText() {
        return GreenText;
    }

    public void setGreenText(Text greenText) {
        GreenText = greenText;
    }

    public Text getRedText() {
        return RedText;
    }

    public void setRedText(Text redText) {
        RedText = redText;
    }

    static boolean isStickFallen = false;
    @FXML
    private Text savedscoretext;

    @FXML
    private Text savedcherriestext;
    private int savedscore = 0;
    private int savedcherries = 0;

    public Text getSavedscoretext() {
        return savedscoretext;
    }

    public void setSavedscoretext(Text savedscoretext) {
        this.savedscoretext = savedscoretext;
    }

    public Text getSavedcherriestext() {
        return savedcherriestext;
    }

    public void setSavedcherriestext(Text savedcherriestext) {
        this.savedcherriestext = savedcherriestext;
    }

    public int getSavedscore() {
        return savedscore;
    }

    public void setSavedscore(int savedscore) {
        this.savedscore = savedscore;
    }

    public int getSavedcherries() {
        return savedcherries;
    }

    public void setSavedcherries(int savedcherries) {
        this.savedcherries = savedcherries;
    }

    public static boolean isIsStickFallen() {
        return isStickFallen;
    }



    public static void setIsStickFallen(boolean isStickFallen) {
        StartPageController.isStickFallen = isStickFallen;
    }

    public void switchtomaingame(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("GAME.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();
        StickClass s1 = StickClass.getInstance();
        PillarFactory f1 = new PillarFactory();
        Object p1 = f1.getPillar(1);
        Pillar p2 = (Pillar) f1.getPillar(2);

        GamePageController gamePageController = fxmlLoader.getController();
        gamePageController.getListofobjects().add(gamePageController.getPlatform());
        gamePageController.getListofobjects().add(gamePageController.getObstacle());
        gamePageController.init_stick();
//        gamePageController.setObstacle(gamePageController.generateObstacle());

        gamePageController.getPausedpane().setVisible(false);
        gamePageController.getGameoverpane().setVisible(false);

        gamePageController.setCherrynumint(0);
        s1.hashCode();
        p1.hashCode();
        p2.hashCode();


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
                if(event.getCode()== KeyCode.F){
                    //gamePageController.updateScore();
                    //gamePageController.updatecherryScore();

                }
                if(event.getCode() == KeyCode.P){
                    gamePageController.pauseonandone();
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE){
                    if (!isStickFallen) {
                        System.out.println("key released");
//                        gamePageController.generatecherry();
                        isStickFallen = true;
                        gamePageController.fallstick();


//                    if (gamePageController.isStickfallen()){
//                        isStickFallen = false;
//                    }
//                    boolean result1 = gamePageController.collision(gamePageController.getStick(), gamePageController.listofobjects.get(1));
//                    boolean result2 = gamePageController.collision(gamePageController.getStick(), gamePageController.getReddot());
//                    if (result1) {
//                        gamePageController.setRightmostpoint_obstacle();
//                        gamePageController.move();
////                        gamePageController.platformTransition();
////                        gamePageController.clearStick();
//                        isStickFallen = false;
//
//                    }
                    }
                }
            }
        });

    }

    public void switchtoloadgame(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("GAME.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();

        GamePageController gamePageController = fxmlLoader.getController();

        String filePath = "savedstate";

        int cherries = 0;
        int score = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the line from the file
            String line = reader.readLine();

            if (line != null) {
                // Split the line into individual values using the comma as a delimiter
                String[] values = line.split(",");

                // Convert the values to integers (assuming the file contains integers)
                score = Integer.parseInt(values[0]);
                cherries = Integer.parseInt(values[1]);

                // Now you have the values, you can use them as needed
                System.out.println("Field 1: " + score);
                System.out.println("Field 2: " + cherries);
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            e.getMessage();
        }


//        SavedStates savee = gamePageController.getSaveedstates();
//        System.out.println(savee.toString());

//        savedcherriestext.setText(String.valueOf(cherries));
//        savedscoretext.setText(String.valueOf(score));

        System.out.println(cherries);
        System.out.println(score);


        gamePageController.setCherrynumint(cherries);
        gamePageController.setScore(score);
        gamePageController.setscoreandcherriesload();


        gamePageController.getListofobjects().add(gamePageController.getPlatform());
        gamePageController.getListofobjects().add(gamePageController.getObstacle());
        gamePageController.init_stick();

//        gamePageController.setObstacle(gamePageController.generateObstacle());

        gamePageController.getPausedpane().setVisible(false);
        gamePageController.getGameoverpane().setVisible(false);

//        gamePageController.setCherrynumint(gamePageController.getSaveedstates().getCherryscore());
//        gamePageController.getCherrynum().setText(String.valueOf((String.valueOf(gamePageController.getSaveedstates().getCherryscore()))));
//
//        gamePageController.setScore(gamePageController.getSaveedstates().getScore());
//        gamePageController.getScoretext().setText(String.valueOf(gamePageController.getSaveedstates().getScore()));


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {

                    if (!isStickFallen) {
                        System.out.println("key pressed");

                        gamePageController.growstick();
                    }
                }
                if (event.getCode() == KeyCode.DOWN) {
                    gamePageController.flipvertical();
                }
                if (event.getCode() == KeyCode.F) {
                    //gamePageController.updateScore();
                    //gamePageController.updatecherryScore();

                }
                if (event.getCode() == KeyCode.P) {
                    gamePageController.pauseonandone();
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    if (!isStickFallen) {
                        System.out.println("key released");
//                        gamePageController.generatecherry();
                        isStickFallen = true;
                        gamePageController.fallstick();


//                    if (gamePageController.isStickfallen()){
//                        isStickFallen = false;
//                    }
//                    boolean result1 = gamePageController.collision(gamePageController.getStick(), gamePageController.listofobjects.get(1));
//                    boolean result2 = gamePageController.collision(gamePageController.getStick(), gamePageController.getReddot());
//                    if (result1) {
//                        gamePageController.setRightmostpoint_obstacle();
//                        gamePageController.move();
////                        gamePageController.platformTransition();
////                        gamePageController.clearStick();
//                        isStickFallen = false;
//
//                    }
                    }
                }
            }
        });

    }

    public void switchtorevivegame(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("GAME.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();

        GamePageController gamePageController = fxmlLoader.getController();

        //gamePageController.setCherrynumint(0);

        gamePageController.setCherrynumint(gamePageController.getCherrynumint()-5);
        gamePageController.setScore(gamePageController.getScore());
        //gamePageController.setscoreandcherriesload();


        gamePageController.getListofobjects().add(gamePageController.getPlatform());
        gamePageController.getListofobjects().add(gamePageController.getObstacle());
        gamePageController.init_stick();

//        gamePageController.setObstacle(gamePageController.generateObstacle());

        gamePageController.getPausedpane().setVisible(false);
        gamePageController.getGameoverpane().setVisible(false);

//        gamePageController.setCherrynumint(gamePageController.getSaveedstates().getCherryscore());
//        gamePageController.getCherrynum().setText(String.valueOf((String.valueOf(gamePageController.getSaveedstates().getCherryscore()))));
//
//        gamePageController.setScore(gamePageController.getSaveedstates().getScore());
//        gamePageController.getScoretext().setText(String.valueOf(gamePageController.getSaveedstates().getScore()));


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {

                    if (!isStickFallen) {
                        System.out.println("key pressed");

                        gamePageController.growstick();
                    }
                }
                if (event.getCode() == KeyCode.DOWN) {
                    gamePageController.flipvertical();
                }
                if (event.getCode() == KeyCode.F) {
                    //gamePageController.updateScore();
                    //gamePageController.updatecherryScore();

                }
                if (event.getCode() == KeyCode.P) {
                    gamePageController.pauseonandone();
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    if (!isStickFallen) {
                        System.out.println("key released");
//                        gamePageController.generatecherry();
                        isStickFallen = true;
                        gamePageController.fallstick();


//                    if (gamePageController.isStickfallen()){
//                        isStickFallen = false;
//                    }
//                    boolean result1 = gamePageController.collision(gamePageController.getStick(), gamePageController.listofobjects.get(1));
//                    boolean result2 = gamePageController.collision(gamePageController.getStick(), gamePageController.getReddot());
//                    if (result1) {
//                        gamePageController.setRightmostpoint_obstacle();
//                        gamePageController.move();
////                        gamePageController.platformTransition();
////                        gamePageController.clearStick();
//                        isStickFallen = false;
//
//                    }
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