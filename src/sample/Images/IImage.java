package sample.Images;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Andriy on 16.04.2016.
 */
public interface IImage {
    byte[] imageToByteArray();

    BufferedImage byteArrayToImage(byte[] arr) throws IOException;
}
