package sample.MenuComands;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.MainController;
import sample.ImageData.Settings;

import java.io.IOException;

/**
 * Created by Andriy on 19.05.2016.
 */
public class SettingCommand implements IComand {
    private AnchorPane pane;
    private Settings settings = MainController.setting;

    public SettingCommand(AnchorPane pane) {
        this.pane = pane;
    }

    @Override
    public void execute() throws IOException {
        Stage stage = new Stage();
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLfiles/setting.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(settings.getCurrentStyleURI());
        stage.setTitle("Settings");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();

    }
}
