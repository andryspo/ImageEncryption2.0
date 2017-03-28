package sample.ArrayCopy;

import java.nio.ByteBuffer;

public class ArrayCopy {

    public static final byte[] intToByteArray(int[] value) {
        ByteBuffer bb = ByteBuffer.allocateDirect(value.length * 4);
        byte res[] = new byte[value.length * 4];

        for (int i = 0; i < value.length; i++) {
            bb.putInt(value[i]);
        }
        bb.flip();
        for (int i = 0; bb.position() < bb.limit(); i++) {
            res[i] = bb.get();

        }
        return res;
    }

    public static int[] toIntArray(byte[] barr) {


        ByteBuffer bb = ByteBuffer.allocateDirect(barr.length);

        int[] res = new int[barr.length / 4];
        for (int i = 0; i < barr.length; i++) {
            bb.put(barr[i]);
        }
        bb.flip();
        for (int i = 0; bb.position() < bb.limit(); i++) {
            res[i] = bb.getInt();
        }
        return res;
    }
}