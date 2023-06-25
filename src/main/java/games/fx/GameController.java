package games.fx;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label lblLeft;

    @FXML
    private Label lblScore;

    int columns = 3;
    int totalNumberOfFrames = 3;
    int FrameWidth = 48;
    int FrameHeight = 42;
    int FPS = 24;
    int rows = 1;

    int columns2 = 4;
    int totalNumberOfFrames2 = 8;
    int FrameWidth2 = 204;
    int FrameHeight2 = 137;
    int FPS2 = 24;
    int rows2 = 2;


    int columns3 = 4;
    int totalNumberOfFrames3 = 4;
    int FrameWidth3 = 40;
    int FrameHeight3 = 48;
    int FPS3 = 24;
    int rows3 = 1;

    protected MediaPlayer mediaPlayer;
    protected Media sound;
    private Enemy enemy1 = new Enemy();
    private Enemy enemy2 = new Enemy();

    private Enemy enemy3 = new Enemy();

    public static double STEP_INCREMENT = 0.0;


    private static IntegerProperty penguinLeft = new SimpleIntegerProperty(30);
    private static IntegerProperty score = new SimpleIntegerProperty(0);

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();

        pane.setOnMouseEntered(event -> {
            pane.setCursor(Cursor.cursor(getClass().getResource("gun.png").toExternalForm())); // replace image_path with the path of your cursor image
        });
        pane.setOnMouseExited(event -> {
            pane.setCursor(Cursor.DEFAULT);
        });

        pane.setOnMouseClicked(event -> {
            if (enemy1.getBoundsInParent().contains(event.getX()-3, event.getY()-3) || enemy1.getBoundsInParent().contains(event.getX()+3, event.getY()+3)) {
                AudioClip clip = null;
                try {
                    clip = new AudioClip(getClass().getResource("shoot.wav").toURI().toString());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                clip.play();
                double x = Math.random() * (800 - (47+30));
                enemy1.setPosition(x,0);
                enemy1.setTranslateY(0);

                penguinLeft.setValue(penguinLeft.getValue()-1);
                score.setValue(score.getValue()+10);
                increaseDifficulty();
            }else if (enemy2.getBoundsInParent().contains(event.getX()-3, event.getY()-3) || enemy2.getBoundsInParent().contains(event.getX()+3, event.getY()+3)) {
                AudioClip clip = null;
                try {
                    clip = new AudioClip(getClass().getResource("shoot.wav").toURI().toString());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                clip.play();
                double x2 = Math.random() * (800 - (47+30));
                enemy2.setPosition(x2,0);
                enemy2.setTranslateY(0);

                penguinLeft.setValue(penguinLeft.getValue()-1);
                score.setValue(score.getValue()+20);
                increaseDifficulty();
            }else if (enemy3.getBoundsInParent().contains(event.getX()-3, event.getY()-3) || enemy3.getBoundsInParent().contains(event.getX()+3, event.getY()+3)) {
                AudioClip clip = null;
                try {
                    clip = new AudioClip(getClass().getResource("shoot.wav").toURI().toString());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                clip.play();
                double x3 = Math.random() * (800 - (47+30));
                enemy3.setPosition(x3,0);
                enemy3.setTranslateY(0);

                penguinLeft.setValue(penguinLeft.getValue()-1);
                score.setValue(score.getValue()+15);
                increaseDifficulty();
            }
        });

        try {
            sound = new Media(getClass().getResource("in_game.mp3").toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        lblLeft.setText(penguinLeft.getValue().toString());
        lblScore.setText(score.getValue().toString());


        pane.getChildren().add(enemy1);
        pane.getChildren().add(enemy2);
        pane.getChildren().add(enemy3);
        double x = Math.random() * (730 );
        double x2 = Math.random() * (730 );
        double x3 = Math.random() * (730 );
        enemy1.setPosition(x,5);
        enemy1.setFitWidth((94/2)*3);
        enemy1.setFitHeight((84/2)*3);
        enemy1.setImage(new Image(getClass().getResource("penguin.png").toExternalForm()));
        ImageViewSprite anim = new ImageViewSprite(enemy1,  columns, rows, totalNumberOfFrames, FrameWidth, FrameHeight, FPS);
        anim.start();

        enemy2.setPosition(x2,5);
        enemy2.setFitWidth((94/2)*3);
        enemy2.setFitHeight((84/2)*3);
        enemy2.setImage(new Image(getClass().getResource("red_dragon.png").toExternalForm()));
        ImageViewSprite anim2 = new ImageViewSprite(enemy2,  columns2, rows2, totalNumberOfFrames2, FrameWidth2, FrameHeight2, FPS2);
        anim2.start();

        enemy3.setPosition(x3,5);
        enemy3.setFitWidth((94/2)*3);
        enemy3.setFitHeight((84/2)*3);
        enemy3.setImage(new Image(getClass().getResource("chicken.png").toExternalForm()));
        ImageViewSprite anim3 = new ImageViewSprite(enemy3,  columns3, rows3, totalNumberOfFrames3, FrameWidth3, FrameHeight3, FPS3);
        anim3.start();

        penguinLeft.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lblLeft.textProperty().bind(penguinLeft.asString());
            }
        });

        score.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lblScore.textProperty().bind(score.asString());
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage = (Stage) pane.getScene().getWindow();

                stage.setOnCloseRequest(e ->{
                    Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
                    dialog.setTitle("Quit Confirmation");
                    dialog.setHeaderText("Confirm quit game");
                    dialog.setContentText("Are you sure you want to quit game.?");
                    ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                    dialog.getButtonTypes().setAll(yesButton, noButton);

                    Optional<ButtonType> result = dialog.showAndWait();
                    if(result.get() != yesButton){
                       e.consume();
                    }
                });
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long l) {

                if(penguinLeft.getValue() < 1){
                    mediaPlayer.stop();
                    try {
                        ScoreManager.addScore(score.get());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.stop();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // Getting the stage from the textfield control


                            mediaPlayer.stop();
                            try {

                                Parent root = FXMLLoader.load(getClass().getResource("game_over.fxml"));

                                stage.setTitle("Game Over");

                                Scene scene = new Scene(root);
                                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                                stage.setScene(scene);


                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    });

                    
                }



                move();
                if(enemy1.getTranslateY() > 768){
                    AudioClip clip = null;
                    try {
                        clip = new AudioClip(getClass().getResource("fall.wav").toURI().toString());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    clip.play();
                    double x = Math.random() * (730);

                    enemy1.setPosition(x,0);
                    enemy1.setTranslateY(0);
                    penguinLeft.setValue(penguinLeft.getValue()-1);
                }else if(enemy2.getTranslateY() > 768){
                    AudioClip clip = null;
                    try {
                        clip = new AudioClip(getClass().getResource("fall.wav").toURI().toString());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    clip.play();
                    double x2 = Math.random() * (730);

                    enemy2.setPosition(x2,0);
                    enemy2.setTranslateY(0);
                    penguinLeft.setValue(penguinLeft.getValue()-1);
                }else if(enemy3.getTranslateY() > 768){
                    AudioClip clip = null;
                    try {
                        clip = new AudioClip(getClass().getResource("fall.wav").toURI().toString());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    clip.play();
                    double x3 = Math.random() * (730);

                    enemy3.setPosition(x3,0);
                    enemy3.setTranslateY(0);
                    penguinLeft.setValue(penguinLeft.getValue()-1);
                }
            }
        }.start();



    }

    private void reset() {
        penguinLeft.setValue(30);
        STEP_INCREMENT = 0.0;
        score.setValue(0);
    }

    public void move(){
        enemy1.setTranslateY(enemy1.getTranslateY()+(int) (1+STEP_INCREMENT));
        enemy2.setTranslateY(enemy2.getTranslateY()+(int) (1+STEP_INCREMENT));
        enemy3.setTranslateY(enemy3.getTranslateY()+(int) (1+STEP_INCREMENT));
    }

    public void increaseDifficulty(){
        STEP_INCREMENT += 0.55;
    }
}
