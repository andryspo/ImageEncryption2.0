package sample.Images;

import javafx.scene.image.*;

import java.io.IOException;

/**
 * Created by Andriy on 16.04.2016.
 */
public class OperatedImage {

    public static byte[] getByteArrayFromImage(Image image) throws IOException {
        PixelReader pr = image.getPixelReader();
        byte[] buffer = null;
        buffer = new byte[(int) (image.getHeight() * image.getWidth()) * 4];
        pr.getPixels(0, 0, (int) image.getWidth(), (int) image.getHeight(), WritablePixelFormat.getByteBgraPreInstance(), buffer, 0, (int) image.getWidth() * 4);
        return buffer;
    }

    public static Image fromByteArrayToImage(byte[] buffer, int width, int height, String path) throws IOException {

        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();
        pw.setPixels(0, 0, width, height, WritablePixelFormat.getByteBgraPreInstance(), buffer, 0, width * 4);
        return img;
    }
}
