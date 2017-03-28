package sample.MenuComands;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.ImageData.ImageViewData;
import sample.Images.OperatedImage;

import java.io.IOException;

/**
 * Created by Andriy on 18.05.2016.
 */
public class SwapCommand implements IComand {

    private ImageViewData imageViewData;
    private ImageView first;
    private ImageView second;

    public SwapCommand(ImageViewData imageViewData, ImageView first, ImageView second) {
        this.imageViewData = imageViewData;
        this.first = first;
        this.second = second;
    }

    @Override
    public void execute() throws IOException {
        if (imageViewData.isOperated()) {
            byte[] imageValue = OperatedImage.getByteArrayFromImage(imageViewData.getOriginalImage());
            byte[] resultValue = OperatedImage.getByteArrayFromImage(imageViewData.getOperatedImage());
            int height = (int) imageViewData.getOriginalImage().getHeight();
            int width = (int) imageViewData.getOriginalImage().getWidth();

            Image image = OperatedImage.fromByteArrayToImage(resultValue, width, height, imageViewData.getPath());
            Image resultImage = OperatedImage.fromByteArrayToImage(imageValue, width, height, imageViewData.getPath());
            imageViewData.setOriginalImage(image);
            imageViewData.setOperatedImage(resultImage);
            first.setImage(image);
            second.setImage(resultImage);
        }
    }
}
