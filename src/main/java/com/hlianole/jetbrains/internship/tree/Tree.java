package com.hlianole.jetbrains.internship.tree;

import java.util.Optional;

public interface Tree {

    Optional<byte[]> get(byte[] key);

    void put(byte[] key, byte[] value);

}
