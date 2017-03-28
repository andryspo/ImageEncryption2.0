package sample.MenuComands;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.MainController;
import sample.ImageData.ImageViewData;
import sample.ImageData.Settings;
import sample.algorythms.Keys.KeyCreator;

import java.io.IOException;

/**
 * Created by Andriy on 17.05.2016.
 */
public class CurrentResultShower implements IComand {
    private static Settings setting;
    private static ImageViewData imageData;
    private static KeyCreator key;
    private AnchorPane pane;
    private Settings settings = MainController.setting;

    public CurrentResultShower(Settings algset, ImageViewData data, AnchorPane pane, KeyCreator keyCreator) {

        imageData = data;
        setting = algset;
        key = keyCreator;
        this.pane = pane;
    }

    @Override
    public void execute() throws IOException {
        Stage stage = new Stage();
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLfiles/show_current_result.fxml"));
        stage.setTitle("Result:");
        scene = new Scene(root);
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(settings.getCurrentStyleURI());
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.setResizable(true);
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();

    }

    public static ImageViewData getImageData() {
        return imageData;
    }

    public static Settings getSetting() {
        return setting;
    }

    public static KeyCreator getKey() {
        return key;
    }
}
