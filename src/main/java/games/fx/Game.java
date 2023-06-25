package games.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("first.fxml"));
        Parent root = loader.load();

        // Set the controller for the FXML file
        FirstController controller = loader.getController();

        // Create a Scene with the Pane as its root
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        // Add the Scene to the Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Falling Angel");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.getIcons().addAll(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
