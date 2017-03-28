package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.ImageData.Settings;

import java.io.IOException;

/**
 * Created by Andriy on 18.05.2016.
 */
public class HelpController {
    @FXML
    private AnchorPane pane;
    private Settings settings = MainController.setting;

    public void aboutAlgorythm(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLfiles/about_algorythms.fxml"));
        stage.setTitle("Result:");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(settings.getCurrentStyleURI());
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();
    }

    public void explain(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLfiles/explain.fxml"));
        stage.setTitle("Explain:");
        stage.setResizable(true);
        Scene scene = new Scene(root);
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(settings.getCurrentStyleURI());
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();

    }
}
