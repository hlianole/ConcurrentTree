package com.hlianole.jetbrains.internship.tree.node;

import com.hlianole.jetbrains.internship.tree.data.Key;
import com.hlianole.jetbrains.internship.tree.data.Value;

/**
 * Implements {@link Node}
 * <p>
 * Represents filled node in the tree. Contains key ({@link Key}),
 * value ({@link Value}) and height (Needed for the AVL tree implementation)
 * <p>
 * Starting with {@link EmptyNode} as parent, left node and right node
 * */
public class FilledNode implements Node {
    private final Key key;
    private volatile Value value;

    private volatile Node left = EmptyNode.getInstance();
    private volatile Node right = EmptyNode.getInstance();
    private volatile Node parent = EmptyNode.getInstance();

    private volatile int height = 1;
    
    public FilledNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node getLeft() {
        return left;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node setLeft(Node node) {
        left = node;
        return this;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node getRight() {
        return right;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node setRight(Node node) {
        right = node;
        return this;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node setParent(Node node) {
        parent = node;
        return this;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Value getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node setValue(Value value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public int compareKey(Key key) {
        return this.key.compare(key);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Node setHeight(int height) {
        this.height = height;
        return this;
    }
}
