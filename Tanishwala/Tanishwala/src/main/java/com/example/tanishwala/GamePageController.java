package com.example.tanishwala;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class GamePageController {

    int StickLength;

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

    @FXML
    private AnchorPane gamepane;
    private double distance;

    @FXML
    Circle cherry;
    private double rightmostpoint_obstacle;

    // stick details
    double stickstartx;
    double stickstarty;
    double stickendY;
    double sticklayx;
    double sticklayy;

    boolean inverted = false;

    public void growstick() {

        System.out.println(Stick.getEndY());
        Stick.setEndY(Stick.getEndY() - 3);
    }


    public void initialisestick() {
        System.out.println("initilaise stick");
        stickstartx = Stick.getStartX();
        stickstarty = Stick.getStartY();
        stickendY = Stick.getEndY();
//        sticklayx = platform.getLayoutX();
//        sticklayy = platform.getLayoutY();
    }


    public void restofthecode() {
        System.out.println("trying to rotate");
        double dist = Stick.getStartY() - Stick.getEndY();
        // Assuming Stick is an instance of javafx.scene.shape.Line

        // Duration of the animation
        Duration duration = Duration.seconds(1);

        // Create a Timeline
        Timeline timeline = new Timeline();

        Rotate rotate=new Rotate();
        System.out.println("pivots: "+stickstartx);
        System.out.println(stickstarty);
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



        timeline.setOnFinished(event -> {
            boolean result1 = collision(getStick(), getObstacle());
            System.out.println(result1);
            if (result1) {
                setRightmostpoint_obstacle();
                System.out.println("i am above move");
                move();
            }
        });

        // Add KeyFrames to the Timeline
        timeline.getKeyFrames().add(rotateKeyFrame);//, collisionKeyFrame);
        timeline.setCycleCount(900);
        // Play the Timeline
        timeline.play();

        distance = dist;
    }
    public void move() {
        System.out.println("moving player");
        double obspos = Obstacle.getLayoutX();
        double platpos = platform.getLayoutX();
        double playerpos = player.getLayoutX();
        double playermovekitna = rightmostpoint_obstacle - 3 - player.getWidth() - player.getLayoutX();

        Duration duration = Duration.millis(10);
        Timeline timeline = new Timeline();
        KeyFrame playerKF = new KeyFrame(duration,event->player.setLayoutX(player.getLayoutX()+1));
        timeline.getKeyFrames().add(playerKF);
        timeline.setCycleCount((int)distance);
        timeline.play();

        timeline.setOnFinished(event -> {
            double playercurrpos = player.getLayoutX();
            moveobstacleback(-(obspos - platpos) + (platform.getWidth() - Obstacle.getWidth()), -(playercurrpos - (stickstartx-player.getWidth())));
        });

        // Play the transition
        timeline.play();
    }

    public void moveobstacleback(double xhilao, double playermovekitna) {
        System.out.println("Moving stuff back again");
        // Obstacle.setWidth(100);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Obstacle);
        transition.setToX(xhilao);
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), platform);
        transition2.setToX(xhilao);
        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(1), player);
        transition3.setToX(playermovekitna);
//        TranslateTransition transition4 = new TranslateTransition(Duration.seconds(1), Stick);
//        transition4.setToX(xhilao);


        clearStick(); // reset stick

        //transition.setAutoReverse(false);
        //transition.setCycleCount(1);
        ParallelTransition parallelTransition = new ParallelTransition(transition, transition2, transition3);

        // Play the parallel transition
        parallelTransition.play();
        platform = Obstacle;
        Obstacle = generatenewobstacle();




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

    public Obstacle generatenewobstacle(){
        System.out.println("generating new obstcle");
        Random random = new Random();
        Obstacle obstacle = new Obstacle(random.nextInt(70 - 20) + 20,156,random.nextInt(266 - 154) + 154,444);
        gamepane.getChildren().add(obstacle);
         // making new obstacle as the odl obstacle
        return obstacle;



    }


    public boolean collision(Line l, Rectangle r) {
        System.out.println("Collision test commence");
        double platform_rightmost_X = platform.getLayoutX() + platform.getWidth();
        double lower_bound = r.getLayoutX() - platform_rightmost_X;
        double upper_bound = lower_bound + r.getWidth();
        System.out.println(lower_bound);
        System.out.println(upper_bound);
        System.out.println(distance);
        if (lower_bound <= distance && distance <= upper_bound) {
            return true;
        }
        return true;
    }

    public void clearStick() {
        System.out.println("clearsticks");
//        Stick.setStartY(181.89999389648438);
        Stick.setEndY(stickendY);
        Stick.setStartX(stickstartx);
        Stick.setStartY(stickstarty);
        Stick.setEndX(stickstartx);

        Stick.getTransforms().clear();
        //Stick.setLayoutX(player.getLayoutX() + player.getWidth() + 2);                   //
    }

    public void setRightmostpoint_obstacle() {
        rightmostpoint_obstacle = Obstacle.getLayoutX() + Obstacle.getWidth();
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

    public Rectangle getPlayer() {
        return player;
    }

    public Rectangle getReddot() {
        return reddot;
    }

    public void switchtopaused(ActionEvent event) throws IOException {
        // System.out.println("we reached here");
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("PAUSED.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero Game");
        stage.setScene(scene);
        stage.show();
    }
    public void generatecherry() {
        cherry.setLayoutY(462);
        Random random = new Random();
        int currentkax = (int) (platform.getLayoutX() + platform.getWidth()) + 15;
        int obstaclekax = (int) Obstacle.getLayoutX() - 15;
        if (obstaclekax - currentkax >= 30) {
            cherry.setLayoutX(random.nextInt(currentkax, obstaclekax));
        } else {
            cherry.setLayoutX(1000);
        }
    }

}


