package com.hlianole.jetbrains.internship.tree.data;

public class Key {

    private final String str;

    public Key(byte[] key) {
        this.str = new String(key);
    }

    public int compare(Key key) {
        return str.compareTo(key.str);
    }
}
