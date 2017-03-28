package sample.algorythms.Keys;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Andriy on 03.05.2016.
 */
public class KeyCreator implements IKeyCreator {

    private byte[] key;

    public KeyCreator() {
        key = new byte[0];
    }

    public void setKey(String k) {

        key = new byte[k.length()];
        for (int i = 0; i < key.length; i++)
            key[i] = (byte) ((k.substring(i, i + 1).getBytes())[0] % 100);

    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getKey() {
        return key;
    }

    @Override
    public byte[] generateKey(int n) {

        Random r = new Random();
        key = new byte[n];
        for (int i = 0; i < n; i++) {
            key[i] = (byte) (r.nextInt(27) % 100);

        }

        return key;
    }

    @Override
    public byte[] getKeyFromFile(String path) {

        int c;
        String k = "";
        try {
            FileReader reader = new FileReader(path);
            while ((c = reader.read()) != -1)
                k += (char) c;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Incorrect file path");

        } catch (IOException e) {
            e.printStackTrace();
        }
        setKey(k);
        return key;
    }

    @Override
    public String toString() {
        return "key: " + key;
    }

    @Override

    public byte[] inputKey(String t) {
        setKey(t);
        return key;
    }
}
