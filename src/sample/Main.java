package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLfiles/main_interface.fxml"));
        stage.setTitle("Image Ecryption 2.0");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
