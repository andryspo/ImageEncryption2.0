package sample.MenuComands;

import sample.ImageData.ImageViewData;
import sample.ImageData.Settings;
import sample.Images.OperatedImage;
import sample.algorythms.Keys.KeyCreator;
import sample.algorythms.RC4.RC4;
import sample.algorythms.Rabbit.Rabbit;
import sample.algorythms.Salsa20.salsa20;
import sample.algorythms.XOR.XOR;

import java.io.IOException;

/**
 * Created by Andriy on 07.05.2016.
 */
public class Encryptor implements IComand {
    private Settings settings;
    private int width;
    private int height;
    private byte[] key;
    private ImageViewData image;

    public Encryptor(Settings settings, KeyCreator key, ImageViewData image) {
        this.settings = settings;
        this.width = (int) image.getOriginalImage().getWidth();
        this.height = (int) image.getOriginalImage().getHeight();
        this.key = key.getKey();
        this.image = image;
    }

    @Override
    public void execute() throws IOException {
        byte[] result = null;
        byte[] value = OperatedImage.getByteArrayFromImage(image.getOriginalImage());
        byte[] f = XOR.f(width, height, key);
        switch (settings.getAlgorythm()) {
            case XOR:

                result = XOR.encrypt(value, key, f);
                break;
            case RC4:

                try {
                    result = RC4.encrypt(value, key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Rabbit:
                Rabbit rabbit = new Rabbit();
                result = rabbit.crypt(value);
                rabbit.reset();
                break;
            case Salsa20:
                System.out.println(key.length);
                salsa20.crypto_stream_xor(value, value, value.length, new byte[8], 0, key);
                result = value;
                break;
        }
        image.setOperatedImage(OperatedImage.fromByteArrayToImage(result, width, height, image.getPath()));
        image.setOperated(true);
    }
}

