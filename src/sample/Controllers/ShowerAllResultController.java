package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.ImageData.ImageViewData;
import sample.MenuComands.AllResultShower;

import java.util.LinkedList;

/**
 * Created by Andriy on 07.05.2016.
 */
public class ShowerAllResultController {
    private final LinkedList<ImageViewData> datas = AllResultShower.getImageDatas();
    private final String algorythmInfo = AllResultShower.getSetting().getAlgorythm().toString();
    private final byte[] key = AllResultShower.getKey().getKey();
    @FXML
    private ScrollPane imagesCBox;
    @FXML
    private Label LabelAlgorythm;
    @FXML
    private TextArea TextAreaKey;

    @FXML
    private void initialize() {
        VBox box = new VBox();
        for (ImageViewData img : datas) {
            HBox hBox = new HBox();
            hBox.getChildren().add(new ImageView(img.getOriginalImage()));
            hBox.getChildren().add(new ImageView(img.getOperatedImage()));
            box.getChildren().add(hBox);
        }
        imagesCBox.setContent(box);

        LabelAlgorythm.setText(LabelAlgorythm.getText() + algorythmInfo);

        TextAreaKey.clear();
        for (int i = 0; i < key.length; i++) {
            TextAreaKey.appendText(key[i] + " ");
        }
    }

}
