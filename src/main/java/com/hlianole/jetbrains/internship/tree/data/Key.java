package com.hlianole.jetbrains.internship.tree.data;

public class Key {

    private final byte[] str;

    public Key(byte[] key) {
        this.str = key;
    }

    public int compare(Key other) {
        byte[] me = this.str;
        byte[] oth = other.str;
        int len = min(me.length, oth.length);

        for (int i = 0; i < len; i++) {
            int cmp = (me[i] & 0xff) - (oth[i] & 0xff);
            if (cmp != 0) {
                return cmp;
            }
        }

        return me.length - oth.length;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }
}
