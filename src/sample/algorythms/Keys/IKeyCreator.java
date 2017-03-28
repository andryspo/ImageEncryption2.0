package sample.algorythms.Keys;

/**
 * Created by Andriy on 03.05.2016.
 */
public interface IKeyCreator {
    byte[] generateKey(int n);

    byte[] getKeyFromFile(String p);

    byte[] inputKey(String s);
}
