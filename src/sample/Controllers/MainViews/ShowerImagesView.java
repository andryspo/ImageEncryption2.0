package sample.Controllers.MainViews;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.ImageData.ImageViewData;

/**
 * Created by Andriy on 16.02.2016.
 */
//display image in different ways it depends on operating
public class ShowerImagesView {
    private final static int NOT_OPERATED_POSITION = 185;
    private final static int OPERATED_POSITION = 50;

    public static void setImages(ImageView first, ImageView second, ImageViewData image, AnchorPane panel) {
        if (image != null) {
            first.setImage(image.getOriginalImage());
            if (image.isOperated() == false) {
                first.setLayoutX(NOT_OPERATED_POSITION);
                panel.getChildren().remove(second);
                second.setImage(null);

            } else {
                panel.getChildren().add(second);
                second.setImage(image.getOperatedImage());
                first.setLayoutX(OPERATED_POSITION);
            }
        }
    }
}
