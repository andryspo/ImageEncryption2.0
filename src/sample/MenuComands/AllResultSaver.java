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
import sample.algorythms.typesOfInput.Algorythms;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Andriy on 17.05.2016.
 */
public class AllResultSaver implements IComand {

    private static LinkedList<ImageViewData> image;
    private AnchorPane pane;
    private static KeyCreator keyCreator;
    private static Algorythms alg;
    private Settings settings = MainController.setting;

    public AllResultSaver(LinkedList<ImageViewData> image, AnchorPane pane, KeyCreator key, Algorythms alg) {
        this.keyCreator = key;
        this.alg = alg;
        this.image = image;
        this.pane = pane;
    }

    @Override
    public void execute() throws IOException {
        Stage stage = new Stage();
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLfiles/all_result_saver.fxml"));
        stage.setTitle("Save all result");
        stage.setResizable(false);
        scene = new Scene(root);
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(settings.getCurrentStyleURI());
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(pane.getScene().getWindow());
        stage.getIcons().add(new Image("/sample/res/ico.jpg"));
        stage.show();
    }

    public static KeyCreator getKeyCreator() {
        return keyCreator;
    }

    public static Algorythms getAlg() {
        return alg;
    }

    public static LinkedList<ImageViewData> getImage() {
        return image;
    }
}
