package sample.MenuComands;/**
 * Created by Andriy on 07.05.2016.
 */

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
import java.util.LinkedList;

public class AllResultShower implements IComand {
    private static Settings setting;
    private static LinkedList<ImageViewData> imageDatas;
    private static KeyCreator key;
    private AnchorPane pane;
    private Settings settings = MainController.setting;

    public AllResultShower(Settings algset, LinkedList<ImageViewData> datas, AnchorPane pane, KeyCreator keyCreator) {

        imageDatas = datas;
        setting = algset;
        key = keyCreator;
        this.pane = pane;
    }

    @Override
    public void execute() throws IOException {
        Stage stage = new Stage();
        Scene scene;
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLfiles/showresult.fxml"));
        stage.setTitle("All result:");
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

    public static LinkedList<ImageViewData> getImageDatas() {
        return imageDatas;
    }

    public static Settings getSetting() {
        return setting;
    }

    public static KeyCreator getKey() {
        return key;
    }
}

