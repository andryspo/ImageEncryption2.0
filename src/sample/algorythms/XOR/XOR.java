package sample.algorythms.XOR;

import sample.algorythms.StreamAlgorythms;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Andriy on 08.12.2015.
 */
public class XOR<T> implements StreamAlgorythms {

    //�������, �� ������ ����������
    //value - ���� ��� ����������
    //s- ����
    //f - �������- ����������� ��� �������� �������
    public static byte[] encrypt(byte[] value, byte[] key, byte f[]) {
        byte[] crypto = new byte[value.length];

        //��������� ����������
        for (int i = 0, k = 0, fi = 0; i < value.length; i++, k++, fi++) {
            if (k > key.length - 1) {
                k = 0;
            }
            if (fi > f.length - 1)
                fi = 0;

            crypto[i] = (byte) ((value[i] ^ key[k]) + f[fi]);
            //    crypto[i] = (byte) (value[i]^key[k]);
        }
        return crypto;
    }


    //�������, �� ������ ����������
    //value - ���� ��� ����������
    //s- ����
    //f - �������- ����������� ��� �������� �������
    public static byte[] decrypt(byte[] value, byte[] key, byte f[]) {
        byte[] decrypto = new byte[value.length];
        for (int i = 0, k = 0, fi = 0; i < value.length; i++, k++, fi++) {
            if (fi > f.length - 1)
                fi = 0;
            value[i] -= f[fi];
            if (k > (key.length - 1)) {
                k = 0;
            }
            decrypto[i] = (byte) (value[i] ^ key[k]);
        }
        return decrypto;
    }

    //������� ������� ��� ���������� �������
    public static byte[] f(int width, int height, byte[] key) {
        byte[] f = new byte[width * height];
        try {

            for (int k = 0, l = 0, p = 0; k < width; k++)
                for (int j = 0; j < height; j++, l++) {
                    if (l == key.length - 1) {
                        l = 0;
                    }
                    f[p] = (byte) (k * j * key[l]);
                    p++;
                }
        } catch (ArrayIndexOutOfBoundsException e2) {
            JOptionPane.showMessageDialog(null, "error with f()");
        }
        return f;
    }

    public static int[] f(int width, int height, int[] key) {
        int[] f = new int[width * height];
        try {

            for (int k = 0, l = 0, p = 0; k < width; k++)
                for (int j = 0; j < height; j++, l++) {
                    if (l == key.length - 1) {
                        l = 0;
                    }
                    f[p] = ((k + 1) * (j + 1) * key[l]);
                    p++;
                }
        } catch (ArrayIndexOutOfBoundsException e2) {
            JOptionPane.showMessageDialog(null, "error with f()");
        }
        return f;
    }

    public static byte[] test_encrypt(ArrayList<Byte> value, byte[] key, byte f[]) {
        byte[] crypto = new byte[value.size()];

        for (int i = 0, k = 0; i < value.size(); i++, k++) {
            if (k > key.length - 1) {
                k = 0;
            }
            crypto[i] = (byte) ((value.get(i) ^ key[k]) + f[i]);
        }
        return crypto;
    }

    public static int[] encrypt(int[] value, int[] key, int f[]) {
        int[] crypto = new int[value.length];

        for (int i = 0, k = 0, fi = 0; i < value.length; i++, k++, fi++) {
            if (k > key.length - 1) {
                k = 0;
            }
            if (fi > f.length - 1)
                fi = 0;

            crypto[i] = (value[i] ^ key[k]) + f[fi];
            //crypto[i] = (value[i]^key[k]);
        }
        return crypto;
    }

    public static int[] decrypt(int[] value, int[] key, int f[]) {
        int[] crypto = new int[value.length];

        for (int i = 0, k = 0, fi = 0; i < value.length; i++, k++, fi++) {
            if (k > key.length - 1) {
                k = 0;
            }
            if (fi > f.length - 1)
                fi = 0;
            value[i] -= f[fi];
            crypto[i] = (value[i] ^ key[k]);
        }
        return crypto;
    }
}

