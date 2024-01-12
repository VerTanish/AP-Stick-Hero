package com.example.tanishwala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;

public class Flatlands extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Media media = new Media("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        FXMLLoader fxmlLoader = new FXMLLoader(Flatlands.class.getResource("START.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 600);
        stage.setTitle("FlatLands");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();

        Result result =
                JUnitCore.runClasses(JunitTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Junit results: "+result.wasSuccessful());

    }


}

