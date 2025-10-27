package com.hlianole.jetbrains.internship.tree.node;

import com.hlianole.jetbrains.internship.tree.data.Key;
import com.hlianole.jetbrains.internship.tree.data.Value;

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

    @Override
    public Node getLeft() {
        return left;
    }

    @Override
    public Node setLeft(Node node) {
        left = node;
        return this;
    }

    @Override
    public Node getRight() {
        return right;
    }

    @Override
    public Node setRight(Node node) {
        right = node;
        return this;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public Node setParent(Node node) {
        parent = node;
        return this;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public Node setValue(Value value) {
        this.value = value;
        return this;
    }

    @Override
    public int compareKey(Key key) {
        return this.key.compare(key);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Node setHeight(int height) {
        this.height = height;
        return this;
    }
}
