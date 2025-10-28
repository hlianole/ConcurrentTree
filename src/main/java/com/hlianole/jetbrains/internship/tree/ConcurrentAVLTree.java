package com.hlianole.jetbrains.internship.tree;

import com.hlianole.jetbrains.internship.tree.data.Key;
import com.hlianole.jetbrains.internship.tree.data.Value;
import com.hlianole.jetbrains.internship.tree.node.EmptyNode;
import com.hlianole.jetbrains.internship.tree.node.FilledNode;
import com.hlianole.jetbrains.internship.tree.node.Node;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Implements {@link Tree}
 * <p>
 * A thread-safe implementation of an AVL tree.
 * This implementation uses a global read-write lock to ensure thread safety during concurrent access.
 * */
public class ConcurrentAVLTree implements Tree {

    protected volatile Node root;
    private final ReentrantReadWriteLock rootLock = new ReentrantReadWriteLock();

    public ConcurrentAVLTree() {
        this.root = EmptyNode.getInstance();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Optional<byte[]> get(byte[] key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        rootLock.readLock().lock();
        try {
            Node found = find(root, new Key(key));
            if (found == EmptyNode.getInstance()) {
                return Optional.empty();
            }
            return Optional.of(found.getValue().value());
        } finally {
            rootLock.readLock().unlock();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(byte[] key, byte[] value) throws IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }

        rootLock.writeLock().lock();
        try {
            root = insert(root, new Key(key), new Value(value), EmptyNode.getInstance());
        } finally {
            rootLock.writeLock().unlock();
        }
    }

    /**
     * Recursively searches for a node with a specific key starting from a given node.
     * */
    private Node find(Node node, Key key) {
        if (node == EmptyNode.getInstance()) {
            return EmptyNode.getInstance();
        }

        int comparison = node.compareKey(key);
        if (comparison < 0) {
            return find(node.getLeft(), key);
        } else if (comparison > 0) {
            return find(node.getRight(), key);
        } else {
            return node;
        }
    }

    /**
     * Recursively inserts a key-value pair into the subtree rooted at the specified node.
     * After insertion, the subtree is rebalanced if necessary to maintain AVL invariants.
     * */
    private Node insert(Node node, Key key, Value value, Node parent) {
        if (node == EmptyNode.getInstance()) {
            return createNode(key, value, parent);
        }

        int comparison = node.compareKey(key);
        if (comparison < 0) {
            node.setLeft(insert(node.getLeft(), key, value, node));
        } else if (comparison > 0) {
            node.setRight(insert(node.getRight(), key, value, node));
        } else {
            return node.setValue(value);
        }

        return balance(node);
    }


    /**
     * Creates a new {@link FilledNode} instance with provided key, value and parent.
     * See {@link FilledNode}
     * */
    private Node createNode(Key key, Value value, Node parent) {
        return new FilledNode(key, value)
                .setParent(parent);
    }

    /**
     * @return balance factor: 0 if left and right subtrees have the same height.
     * Greater than 0 if the right subtree is higher.
     * Less than 0 if the left subtree is higher
     * */
    private int balanceFactor(Node node) {
        return node.getRight().getHeight() - node.getLeft().getHeight();
    }

    /**
     * Updates the node's height depending on the node's subtrees. The height is updated from bottom to up
     * */
    private void updateHeight(Node node) {
        node.setHeight(
                Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1
        );
    }

    /**
     * Left AVL rotation
     *
     * @return the node which replaces the given node after rotation
     * */
    private Node rotateLeft(Node node) {
        Node right = node.getRight();
        Node rightLeft = right.getLeft();

        right.setLeft(node);
        node.setRight(rightLeft);

        right.setParent(node.getParent());
        node.setParent(right);

        if (rightLeft != EmptyNode.getInstance()) {
            rightLeft.setParent(node);
        }

        updateHeight(node);
        updateHeight(right);

        return right;
    }

    /**
     * Right AVL rotation
     *
     * @return the node which replaces the given node after rotation
     * */
    private Node rotateRight(Node node) {
        Node left = node.getLeft();
        Node leftRight = left.getRight();

        left.setRight(node);
        node.setLeft(leftRight);

        left.setParent(node.getParent());
        node.setParent(left);

        if (leftRight != EmptyNode.getInstance()) {
            leftRight.setParent(node);
        }

        updateHeight(node);
        updateHeight(left);

        return left;
    }

    /**
     * AVL balancing. Provides height updating for the node and executes single or double rotation depending
     * on the balance factor if needed.
     * */
    private Node balance(Node node) {
        updateHeight(node);

        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1) {
            if (balanceFactor(node.getRight()) < 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        else if (balanceFactor < -1) {
            if (balanceFactor(node.getLeft()) > 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        return node;
    }
}