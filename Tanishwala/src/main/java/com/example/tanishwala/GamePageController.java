package com.example.tanishwala;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import com.example.tanishwala.StartPageController.REVIVEbutton;
//import static com.example.tanishwala.StartPageController.RedText;
//import static com.example.tanishwala.StartPageController.GreenText;

public class GamePageController implements Serializable {

    public static boolean werethereenufcherries = false;

    @FXML
    private AnchorPane gameoverpane;

    public AnchorPane getGameoverpane() {
        return gameoverpane;
    }

    public void setGameoverpane(AnchorPane gameoverpane) {
        this.gameoverpane = gameoverpane;
    }

    public AnchorPane getPausedpane() {
        return pausedpane;
    }

    public Text getCherrynum() {
        return cherrynum;
    }

    public void setCherrynum(Text cherrynum) {
        this.cherrynum = cherrynum;
    }

    public void setPausedpane(AnchorPane pausedpane) {
        this.pausedpane = pausedpane;
    }

    public AnchorPane getGamepane() {
        return gamepane;
    }

    public void setGamepane(AnchorPane gamepane) {
        this.gamepane = gamepane;
    }

    @FXML
    private AnchorPane pausedpane;

    @FXML
    private AnchorPane gamepane;
    @FXML
    private Circle cherry;
    @FXML
    private Text scoretext;
    private int score = 0;
    @FXML
    private Text cherrynum;
    private int cherrynumint = 0;

    public int getCherrynumint() {
        return cherrynumint;
    }

    public void setCherrynumint(int cherrynumint) {
        this.cherrynumint = cherrynumint;
    }

    private ArrayList<Rectangle> listofobjects = new ArrayList<>();

    private ScheduledExecutorService scheduler;

    private SavedStates saveedstates = null;


    public SavedStates getSaveedstates() {
        return saveedstates;
    }

    public void setSaveedstates(SavedStates saveedstates) {
        this.saveedstates = saveedstates;
    }

    public void initialize() {
        // Initialize the scheduler and schedule the color change task
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::changecherrycolour, 0, 3, TimeUnit.SECONDS);

        // ... Other initialization code ...
    }

    @FXML
    private Line Stick;
    @FXML
    private Rectangle Obstacle;
    @FXML
    private Rectangle reddot;
    @FXML
    private Rectangle player;
    @FXML
    private Rectangle platform;
    private double distance;
    private double rightmostpoint_obstacle;
    private boolean isfallen = false;
    private double global_startx,global_starty,global_endx,global_endy;

    boolean inverted = false;

    public Circle getCherry() {
        return cherry;
    }

    public void setRightmostpoint_obstacle() {
        rightmostpoint_obstacle = listofobjects.get(1).getLayoutX() + listofobjects.get(1).getWidth();
    }

    public ArrayList<Rectangle> getListofobjects() {
        return listofobjects;
    }

    public void setListofobjects(ArrayList<Rectangle> listofobjects) {
        this.listofobjects = listofobjects;
    }

    public void pauseonandone() {
        try {
            AnchorPane pausedPane = getPausedpane();
            if (pausedPane != null) {
                if (pausedPane.isVisible()) {
                    pausedPane.setVisible(false);
                } else {
                    pausedPane.setVisible(true);
                }
                StartPageController.setIsStickFallen(!StartPageController.isIsStickFallen());
            } else {
                System.err.println("Paused pane is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameover(){
        getGameoverpane().setVisible(true);

    }

    public void notdeadyet(){
        getGameoverpane().setVisible(false);
    }

    @FXML
    private Text notEnoughCurrency;

    public Text getNotEnoughCurrency() {
        return notEnoughCurrency;
    }

    public void setNotEnoughCurrency(Text notEnoughCurrency) {
        this.notEnoughCurrency = notEnoughCurrency;
    }

    public void tryluck(){
        int currentcherries = cherrynumint;
        if(cherrynumint > 5){
            notdeadyet();
        }
        else{
            notEnoughCurrency.setVisible(true);
            System.out.println("noe enoguh currency");
            revive();

        }

    }

    public void revive() {
        try {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), player);
            transition.setToY(-620);
            SequentialTransition seq = new SequentialTransition(transition);

            seq.setOnFinished(event -> {
                clearStick();
                //StartPageController.setIsStickFallen(true);
            });

            seq.play();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }



    public void setObstacle(Rectangle obstacle) {
        Obstacle = obstacle;
    }

    public void setStick(Line stick) {
        Stick = stick;
    }

    public void setPlayer(Rectangle player) {
        this.player = player;
    }

    public void setPlatform(Rectangle platform) {
        this.platform = platform;
    }

    public Line getStick() {
        return Stick;
    }

    public Rectangle getObstacle() {
        return Obstacle;
    }

    public Rectangle getPlatform() {
        return platform;
    }

    public void updateScore() {
        score++;  // Increment the score (you can modify this based on your game logic)
        if (score <10){
            scoretext.setText("0"+score);
        }

        else{
            scoretext.setText(String.valueOf(score));  // Update the text in the Text element
        }
    }

    public void setscoreandcherriesload(){
        if (score <10){
            scoretext.setText("0"+score);
        }

        else{
            scoretext.setText(String.valueOf(score));
        }
        if (cherrynumint <10){
            cherrynum.setText("0"+cherrynumint);
        }

        else{
            cherrynum.setText(String.valueOf(cherrynumint));
        }
        //cherrynum.setText(String.valueOf(cherrynumint));
    }




    public Text getScoretext() {
        return scoretext;
    }

    public void setScoretext(Text scoretext) {
        this.scoretext = scoretext;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updatecherryScore() {
        cherrynumint++;  // Increment the score (you can modify this based on your game logic)
        if (cherrynumint<10){
            cherrynum.setText("0"+cherrynumint);
        }

        else{
            cherrynum.setText(String.valueOf(cherrynumint));  // Update the text in the Text element
        }
    }

    public Rectangle getPlayer() {
        return player;
    }

    public Rectangle getReddot() {
        return reddot;
    }

    public boolean isfallen() {
        return isfallen;
    }

    public void setFallen(boolean f){
        isfallen = f;
    }

    public boolean check5cherries(){
        if(cherrynumint>=5){
            return true;
        }
        else{
            return false;
        }
    }

    public void move(){
        boolean r1 = collision(getStick(), listofobjects.get(1));
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), player);
        generatecherry();

        boolean check5 = check5cherries();

        if(r1 ){
            transition.setToX(rightmostpoint_obstacle - 3 - player.getWidth() - player.getLayoutX());
            SequentialTransition seq = new SequentialTransition(transition);
            seq.setOnFinished(e -> {
                platformTransition();
                getCherryCollision();
            });
            seq.play();//distance + 3/4 object width

        }
        else if(!r1 && check5){

            player.setFill(Color.valueOf("#FFC0CB"));
            cherrynum.setText(String.valueOf(cherrynumint-5));
            cherrynumint = cherrynumint-5;

            transition.setToX(rightmostpoint_obstacle - 3 - player.getWidth() - player.getLayoutX());
            SequentialTransition seq = new SequentialTransition(transition);
            seq.setOnFinished(e -> {
                platformTransition();
                getCherryCollision();
            });
            seq.play();//distance + 3/4 object width

        }

        else if(!r1 && !check5){

            transition.setToX(distance -player.getWidth());
            SequentialTransition seq = new SequentialTransition(transition);
            seq.setOnFinished(e -> {
                player.setFill(Color.valueOf("#ff0000"));

                //getCherryCollision();
                skyfall();

            });
            seq.play();

        }



    }


    public void growstick(){
        if (!isfallen) {
            System.out.println(Stick.getEndY());
            Stick.setEndY(Stick.getEndY() - 3);
            //notEnoughCurrency.setVisible(false);
        }
    }

    public void skyfall(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1),player);
        transition.setToY(620);
        SequentialTransition seq = new SequentialTransition(transition);

        seq.setOnFinished(event -> {
            //
            gameover();
            StartPageController.setIsStickFallen(false);
        });

        seq.play();

    }



    public void savemystate(){
        saveedstates = new SavedStates(getScore(),getCherrynumint());
        System.out.println(saveedstates.toString());

        String filePath = "savedstate";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Format the values as a CSV string
            String csvString = getScore()+","+getCherrynumint();

            // Write the CSV string to the file
            fileWriter.write(csvString);

            // Explicitly flush and close the FileWriter
            fileWriter.flush();
            fileWriter.close();

            System.out.println("Values have been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadstate(){


    }

    public void fallstick(){
        distance = Stick.getStartY() - Stick.getEndY() + 3;
        isfallen = true;

//        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), Stick);
//        translateTransition.setToY(distance/2 + Stick.getStrokeWidth()); // Move down by stick height
//        translateTransition.setByX(distance/2);
//
//        // Create a rotate transition for the rotation animation
//        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), Stick);
//        rotateTransition.setByAngle(90); // Rotate by 90 degrees
//
//        // Play both animations concurrently
//        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, rotateTransition);
//        parallelTransition.setOnFinished(event -> {
//            boolean r1 = collision(getStick(), listofobjects.get(1));
//            boolean r2 = collision(getStick(), reddot);
//            r1 = true;
//            if (r1){
//                setRightmostpoint_obstacle();
//                move();
//                isStickfallen = true;
//            }
//        });
//        parallelTransition.play();

        Duration duration = Duration.seconds(1);

        // Create a Timeline
        Timeline timeline = new Timeline();
        Rotate rotate=new Rotate();
        rotate.setPivotX(Stick.getStartX());
        rotate.setPivotY(Stick.getStartY());
        rotate.setAngle(0.1);
        // Add KeyFrame for rotation
        KeyFrame rotateKeyFrame = new KeyFrame(Duration.millis(1), event ->
        {
            //System.out.println("Rotate");
            Stick.getTransforms().add(rotate);
            //System.out.println(rotate.getPivotX()+" "+rotate.getAngle());
        });
        timeline.getKeyFrames().add(rotateKeyFrame);//, collisionKeyFrame);
        timeline.setCycleCount(900);
        timeline.setOnFinished(event -> {
            boolean r1 = collision(getStick(), listofobjects.get(1));
            boolean r2 = collision(getStick(), reddot);
            setRightmostpoint_obstacle();
            move();
            isfallen = false;
            if (r1) {
                updateScore();
            }
            if (r2){
                updateScore();
            }
        });
        // Play the Timeline
        timeline.play();
    }

    public Obstacle generateObstacle(){
//        Obstacle.setHeight(156);
//        Random random = new Random();
//        Obstacle.setWidth(random.nextInt(70-20) + 20);
////        Obstacle.setX(random.nextInt(19 - (-99)) + (-99));
//        Obstacle.setLayoutX(random.nextInt(266 - 154) + 154);
//        reddot.setLayoutX(Obstacle.getLayoutX() + (Obstacle.getWidth()/2) - (reddot.getWidth()/2));
        Random random = new Random();
        String[] SHADES_OF_GREEN = {
                "#708238", // olive
                "#008080", // Teal
                "#32de84", //android
                "#DADD98", //pale green
                "#66CDAA", //aqua



        };


        Random random2 = new Random();
        // Select a random color from the array
        String randomColor = SHADES_OF_GREEN[random2.nextInt(SHADES_OF_GREEN.length)];
        Obstacle obstacle = new Obstacle(random.nextInt(70 - 20) + 20,156,random.nextInt(266 - 154) + 154,444);
        obstacle.setFill(Color.valueOf(randomColor));
        reddot.setLayoutX(obstacle.getLayoutX() + (obstacle.getWidth()/2) - (reddot.getWidth()/2));
        reddot.setY(-3);
        gamepane.getChildren().add(obstacle);
//        gamepane.getChildren().remove(platform);
        // making new obstacle as the odl obstacle
        return obstacle;

    }

    public boolean collision(Line l, Rectangle r){
        Rectangle _platform = listofobjects.get(0);
//        double starting_pos = 24 + listofobjects.get(0).getWidth() - 16;
////        double platform_rightmost_X = _platform.getLayoutX() + _platform.getWidth();
        System.out.println(player.getLayoutX());
        double lower_bound = r.getLayoutX() - (40 + _platform.getWidth());
        System.out.println("X: " + r.getLayoutX() + "plat: " + listofobjects.get(0).getLayoutX());
        double upper_bound = lower_bound + r.getWidth();
        System.out.println("lower" +lower_bound);
        System.out.println("upper"+upper_bound);
        System.out.println("X"+distance);
        if (lower_bound <= distance && distance <= upper_bound){
            return true;
        }
        return false;
    }

    public void clearStick(){
        Stick.setStartY(global_starty);
        Stick.setEndY(global_endy);
        Stick.setStartX(20 + listofobjects.get(0).getWidth());
        Stick.setEndX(20 + listofobjects.get(0).getWidth());

        Stick.getTransforms().clear();
//        reddot.setLayoutX(listofobjects.get(1));
    }

    public void platformTransition(){
//        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Rectangle _platform = listofobjects.get(0);
        Rectangle _obstacle = listofobjects.get(1);
        TranslateTransition trans = new TranslateTransition(Duration.seconds(1), _obstacle);
        double temp = 40 - _obstacle.getLayoutX();
        trans.setToX(temp);
        TranslateTransition play = new TranslateTransition(Duration.seconds(1), player);
        play.setToX(40 -player.getLayoutX() - player.getWidth() + _obstacle.getWidth());
//        play.setToX(temp);
        TranslateTransition plat = new TranslateTransition(Duration.seconds(1), _platform);
        plat.setToX(-900);
        reddot.setVisible(false);
        Stick.setVisible(false);
        ParallelTransition parallelTransition = new ParallelTransition(trans, plat, play);
//        PauseTransition pause = new PauseTransition(Duration.seconds(2));
//        SequentialTransition seq = new SequentialTransition(pause, parallelTransition);
        parallelTransition.setOnFinished(e -> {
            Obstacle obs = generateObstacle();
//            setPlatform(Obstacle);
            listofobjects.set(0, listofobjects.get(1));
            listofobjects.set(1, obs);
            gamepane.getChildren().remove(platform);
            setObstacle(obs);
            reddot.setVisible(true);
            Stick.setVisible(true);
            clearStick();
            StartPageController.setIsStickFallen(!StartPageController.isIsStickFallen());
//            Stick.setStartX(24 + listofobjects.get(0).getWidth());
//            Stick.setEndX(24 + listofobjects.get(0).getWidth());
        });

//        parallelTransition.setOnFinished(event -> {
//            System.out.println("meowsexy");
//        });


        parallelTransition.play();



//        platform.setWidth(0);
//        Obstacle.setLayoutX(122 - Obstacle.getWidth());
    }

    public void init_stick(){
        global_startx = Stick.getStartX();
        global_starty = Stick.getStartY();
        global_endx = Stick.getEndX();
        global_endy = Stick.getEndY();
    }

    public void flipvertical(){
        System.out.println("flip");
        player.setScaleY(-1);
        if (inverted == false){
            player.setY(player.getY() + player.getWidth());        }
        else{
            player.setY(player.getY() - player.getWidth());        }
        inverted = !inverted;
    }

    public void generatecherry() {
        cherry.setLayoutY(455);
        cherry.setVisible(true);
        Random random = new Random();
//        int currentkax = (int) (platform.getLayoutX() + platform.getWidth()) + 15;
        int obstaclekax = (int) listofobjects.get(1).getLayoutX();
        int currentkax = obstaclekax - 50;
        if (obstaclekax - currentkax >= 30) {
            BallCherry cherry0 = new BallCherry(random.nextInt(currentkax, obstaclekax));
            cherry.setLayoutX(random.nextInt(currentkax, obstaclekax));
        } else {
            BallCherry cherry0 = new BallCherry(1000);
            cherry.setLayoutX(1000);
        }

    }

    public void changecherrycolour(){
        Random random = new Random();
        int change = random.nextInt(2);
        if (change == 1){
            cherry.setFill(Color.valueOf("#ff219d"));
        }
        else{
            cherry.setFill(Color.valueOf("#fffa1f"));
        }
        //cherry.setFill(Color.valueOf("#ff219d"));
    }

    public void switchtoPAUSED(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("PAUSED.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoSTART(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        setFallen(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("START.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();

        //isfallen = false;
    }

    public void switchtoREVIVE(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        setFallen(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("REVIVE.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();

        //isfallen = false;
    }

    public void getCherryCollision(){
//        Shape inter = Shape.intersect(player, cherry);
        if (true){
            updatecherryScore();
            cherry.setVisible(false);
        }
        else{
            cherry.setVisible(false);
        }
    }
}