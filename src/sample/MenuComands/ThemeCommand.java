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
 * Created by Andriy on 23.05.2016.
 */
public class ThemeCommand implements IComand {
    private AnchorPane pane;
    private static Settings settings = MainController.setting;

    @Override
    public void execute() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("../FXMLfiles/theme.fxml"));
        stage.setTitle("Theme:");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.setResizable(true);
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();
    }

    public static Settings getSettings() {
        return settings;
    }

    public ThemeCommand(AnchorPane pane) {
        this.pane = pane;
    }
}
