package sample.algorythms.Salsa20;


import sample.algorythms.StreamAlgorythms;

public class hsalsa20 implements StreamAlgorythms {
    static final int ITERATION = 20;

    static int cycling_Shift(int u, int c) {
        return (u << c) | (u >>> (32 - c));
    }

    static int load_littleendian(byte[] x, int offset) {
        return ((int) (x[offset]) & 0xff) |
                ((((int) (x[offset + 1]) & 0xff)) << 8) |
                ((((int) (x[offset + 2]) & 0xff)) << 16) |
                ((((int) (x[offset + 3]) & 0xff)) << 24);
    }

    static void store_littleendian(byte[] x, int offset, int u) {
        x[offset] = (byte) u;
        u >>>= 8;
        x[offset + 1] = (byte) u;
        u >>>= 8;
        x[offset + 2] = (byte) u;
        u >>>= 8;
        x[offset + 3] = (byte) u;
    }

    public static int crypto_core(byte[] outv, byte[] inv, byte[] k, byte[] c) {
        int x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15;
        int j0, j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15;
        int i;

        j0 = x0 = load_littleendian(c, 0);
        j1 = x1 = load_littleendian(k, 0);
        j2 = x2 = load_littleendian(k, 4);
        j3 = x3 = load_littleendian(k, 8);
        j4 = x4 = load_littleendian(k, 12);
        j5 = x5 = load_littleendian(c, 4);

        if (inv != null) {
            j6 = x6 = load_littleendian(inv, 0);
            j7 = x7 = load_littleendian(inv, 4);
            j8 = x8 = load_littleendian(inv, 8);
            j9 = x9 = load_littleendian(inv, 12);
        } else {
            j6 = x6 = j7 = x7 = j8 = x8 = j9 = x9 = 0;
        }

        j10 = x10 = load_littleendian(c, 8);
        j11 = x11 = load_littleendian(k, 16);
        j12 = x12 = load_littleendian(k, 20);
        j13 = x13 = load_littleendian(k, 24);
        j14 = x14 = load_littleendian(k, 28);
        j15 = x15 = load_littleendian(c, 12);

        for (i = ITERATION; i > 0; i -= 2) {
            x4 ^= cycling_Shift(x0 + x12, 7);
            x8 ^= cycling_Shift(x4 + x0, 9);
            x12 ^= cycling_Shift(x8 + x4, 13);
            x0 ^= cycling_Shift(x12 + x8, 18);
            x9 ^= cycling_Shift(x5 + x1, 7);
            x13 ^= cycling_Shift(x9 + x5, 9);
            x1 ^= cycling_Shift(x13 + x9, 13);
            x5 ^= cycling_Shift(x1 + x13, 18);
            x14 ^= cycling_Shift(x10 + x6, 7);
            x2 ^= cycling_Shift(x14 + x10, 9);
            x6 ^= cycling_Shift(x2 + x14, 13);
            x10 ^= cycling_Shift(x6 + x2, 18);
            x3 ^= cycling_Shift(x15 + x11, 7);
            x7 ^= cycling_Shift(x3 + x15, 9);
            x11 ^= cycling_Shift(x7 + x3, 13);
            x15 ^= cycling_Shift(x11 + x7, 18);
            x1 ^= cycling_Shift(x0 + x3, 7);
            x2 ^= cycling_Shift(x1 + x0, 9);
            x3 ^= cycling_Shift(x2 + x1, 13);
            x0 ^= cycling_Shift(x3 + x2, 18);
            x6 ^= cycling_Shift(x5 + x4, 7);
            x7 ^= cycling_Shift(x6 + x5, 9);
            x4 ^= cycling_Shift(x7 + x6, 13);
            x5 ^= cycling_Shift(x4 + x7, 18);
            x11 ^= cycling_Shift(x10 + x9, 7);
            x8 ^= cycling_Shift(x11 + x10, 9);
            x9 ^= cycling_Shift(x8 + x11, 13);
            x10 ^= cycling_Shift(x9 + x8, 18);
            x12 ^= cycling_Shift(x15 + x14, 7);
            x13 ^= cycling_Shift(x12 + x15, 9);
            x14 ^= cycling_Shift(x13 + x12, 13);
            x15 ^= cycling_Shift(x14 + x13, 18);
        }

        x0 += j0;
        x1 += j1;
        x2 += j2;
        x3 += j3;
        x4 += j4;
        x5 += j5;
        x6 += j6;
        x7 += j7;
        x8 += j8;
        x9 += j9;
        x10 += j10;
        x11 += j11;
        x12 += j12;
        x13 += j13;
        x14 += j14;
        x15 += j15;

        x0 -= load_littleendian(c, 0);
        x5 -= load_littleendian(c, 4);
        x10 -= load_littleendian(c, 8);
        x15 -= load_littleendian(c, 12);

        if (inv != null) {
            x6 -= load_littleendian(inv, 0);
            x7 -= load_littleendian(inv, 4);
            x8 -= load_littleendian(inv, 8);
            x9 -= load_littleendian(inv, 12);
        }

        store_littleendian(outv, 0, x0);
        store_littleendian(outv, 4, x5);
        store_littleendian(outv, 8, x10);
        store_littleendian(outv, 12, x15);
        store_littleendian(outv, 16, x6);
        store_littleendian(outv, 20, x7);
        store_littleendian(outv, 24, x8);
        store_littleendian(outv, 28, x9);

        return 0;
    }
}
