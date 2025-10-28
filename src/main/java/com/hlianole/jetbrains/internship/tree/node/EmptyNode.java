package com.hlianole.jetbrains.internship.tree.node;

import com.hlianole.jetbrains.internship.tree.data.Key;
import com.hlianole.jetbrains.internship.tree.data.Value;

/**
 * Implements {@link Node}
 * <p>
 * Represents an empty node in the tree. Use {@link EmptyNode#getInstance()} to get an empty instance of the node
 * */
public final class EmptyNode implements Node {

    private static EmptyNode INSTANCE;

    public static EmptyNode getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmptyNode();
        }
        return INSTANCE;
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node getLeft() {
        throw new UnsupportedOperationException("Trying to get the LEFT of the empty node");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node setLeft(Node node) {
        throw new UnsupportedOperationException("Trying to set the LEFT of the empty node");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node getRight() {
        throw new UnsupportedOperationException("Trying to get the RIGHT of the empty node");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node setRight(Node node) {
        throw new UnsupportedOperationException("Trying to set the RIGHT of the empty node");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node getParent() {
        throw new UnsupportedOperationException("Trying to get the PARENT of the empty node !");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node setParent(Node node) {
        throw new UnsupportedOperationException("Trying to set the PARENT of the empty node !");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Value getValue() {
        throw new UnsupportedOperationException("Trying to get the VALUE of the empty node");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node setValue(Value value) {
        throw new UnsupportedOperationException("Trying to set the VALUE of the empty node");
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public int compareKey(Key key) {
        throw new UnsupportedOperationException("Trying to compare the key of the empty node");
    }

    /**
     * @return 0. An empty node has the height of 0
     * */
    @Override
    public int getHeight() {
        return 0;
    }

    /**
     * {@throws UnsupportedOperationException}
     * <p>
     * Unsupported operation in the empty node
     * */
    @Override
    public Node setHeight(int height) {
        throw new UnsupportedOperationException("Trying to set the HEIGHT of the empty node");
    }
}
