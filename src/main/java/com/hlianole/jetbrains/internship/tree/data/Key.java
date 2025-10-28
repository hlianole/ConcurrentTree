package com.hlianole.jetbrains.internship.tree.data;

/**
 * A wrapper class representing a key composed of raw bytes.
 * Keys a compared lexicographically using unsigned byte comparison.
 * */
public class Key {

    private final byte[] str;

    public Key(byte[] key) {
        this.str = key;
    }

    /**
     * Compares this key to another key lexicographically using unsigned byte comparison.
     *
     * @return 0 in case keys are similar.
     * Less than 0 in case this key is lex. less than another key.
     * Greater than 0 in case this key is greater than another key.
     * */
    public int compare(Key other) {
        byte[] me = this.str;
        byte[] oth = other.str;
        int len = min(me.length, oth.length);

        for (int i = 0; i < len; i++) {
            int cmp = (oth[i] & 0xff) - (me[i] & 0xff);
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
