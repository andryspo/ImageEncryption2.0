package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.ImageData.ImageViewData;
import sample.MenuComands.CurrentResultShower;

/**
 * Created by Andriy on 17.05.2016.
 */
public class ShowerCurrentResultController {
    private final ImageViewData data = CurrentResultShower.getImageData();
    private final String algorythmInfo = CurrentResultShower.getSetting().getAlgorythm().toString();
    private final byte[] key = CurrentResultShower.getKey().getKey();
    @FXML
    private ScrollPane imagesCBox_;
    @FXML
    private Label LabelAlgorythm_;
    @FXML
    private TextArea TextAreaKey_;

    @FXML
    private void initialize() {
        VBox box = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().add(new ImageView(data.getOriginalImage()));
        hBox.getChildren().add(new ImageView(data.getOperatedImage()));
        box.getChildren().add(hBox);

        imagesCBox_.setContent(box);

        LabelAlgorythm_.setText(LabelAlgorythm_.getText() + algorythmInfo);

        TextAreaKey_.clear();
        for (int i = 0; i < key.length; i++) {
            TextAreaKey_.appendText(key[i] + " ");
        }
    }
}
