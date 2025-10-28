package com.hlianole.jetbrains.internship.tree;

import java.util.Optional;

/**
 * Represents a key-value storage interface using byte[] arrays as keys and values.
 * Basic operations for storing and retrieving data are provided.
 * */
public interface Tree {

    /**
     * Retrieves the value associated this the provided key
     *
     * @param key byte[]. Must not be {@code null}
     * @return an {@link Optional} containing a value if the key exists, or {@link Optional#empty()} otherwise
     * */
    Optional<byte[]> get(byte[] key);

    /**
     * Associates the provided value with the key
     *
     * @param key byte[]. Must not be {@code null}
     * @param value byte[]. Must not be {@code null}
     * */
    void put(byte[] key, byte[] value);

}
