package games.fx;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    @FXML
    private Button btnEnd;

    @FXML
    private Button btnStart;

    @FXML
    private Label lblFallen;

    @FXML
    private Label lblTop;


    @FXML
    private VBox vbox;


    protected MediaPlayer mediaPlayer;
    protected Media sound;


    private Font fontx = Font.loadFont(getClass().getResourceAsStream("evil_empire.ttf"), 22);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            List<Integer> topScores = ScoreManager.getScores();
            if(topScores.size() < 1){
                Label l = new Label();
                l.setText("No scores available");
                l.setFont(fontx);
                vbox.getChildren().add(l);
                return;
            }
            int count = 1;
            for(int score: topScores){
                Label l = new Label();
                l.setText("Top "+count+": "+score);
                l.setFont(fontx);
                vbox.getChildren().add(l);
                count++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            sound = new Media(getClass().getResource("game_over.wav").toURI().toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer = new MediaPlayer(sound);
       // mediaPlayer.setCycleCount(MediaPlayer.);
        mediaPlayer.play();

        btnStart.setOnAction(e ->{
            AudioClip clip = null;
            try {
                clip = new AudioClip(getClass().getResource("select.wav").toURI().toString());
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            clip.play();


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Getting the stage from the textfield control
                    Stage stage = (Stage) btnStart.getScene().getWindow();

                    mediaPlayer.stop();
                    try {

                        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));

                        stage.setTitle("Fallen Angel");

                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                        stage.setScene(scene);


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
        });

        btnEnd.setOnAction(e ->{
            AudioClip clip = null;
            try {
                clip = new AudioClip(getClass().getResource("select.wav").toURI().toString());
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            clip.play();


            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Quit Confirmation");
            dialog.setHeaderText("Confirm quit game");
            dialog.setContentText("Are you sure you want to quit game.?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            dialog.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if(result.get() == yesButton){
                System.exit(0);
            }
        });

        try {
            Font font = Font.loadFont(getClass().getResourceAsStream("evil_empire.ttf"), 42);
            Font font2 = Font.loadFont(getClass().getResourceAsStream("evil_empire.ttf"), 18);
            lblFallen.setFont(font);
            lblTop.setFont(font);
            btnEnd.setFont(font2);
            btnStart.setFont(font2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
