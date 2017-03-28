package sample.Controllers.MainViews;

import javafx.scene.image.ImageView;

/**
 * Created by Andriy on 16.04.2016.
 */
public class OriginalAndOperatedImagesViews {

    private ImageView originalImage;
    private ImageView operatedrImage;

    public OriginalAndOperatedImagesViews(ImageView originalImage, ImageView operatedrImage) {

        this.originalImage = originalImage;
        this.operatedrImage = operatedrImage;
    }

    public ImageView getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(ImageView originalImage) {
        this.originalImage = originalImage;
    }

    public ImageView getOperatedImage() {
        return operatedrImage;
    }

    public void setOperatedrImage(ImageView operatedrImage) {
        this.operatedrImage = operatedrImage;
    }
}
