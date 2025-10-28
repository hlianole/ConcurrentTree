package com.hlianole.jetbrains.internship.tree.node;

import com.hlianole.jetbrains.internship.tree.data.Key;
import com.hlianole.jetbrains.internship.tree.data.Value;

/**
 * Represents a node in a binary search tree structure.
 * */
public interface Node {

    /**
     * @return the left child of the node
     * */
    Node getLeft();

    /**
     * Sets the left child of the node
     * */
    Node setLeft(Node node);

    /**
     * @return the right child of the node
     * */
    Node getRight();

    /**
     * Sets the right child of the node
     * */
    Node setRight(Node node);

    /**
     * @return the parent of the node
     * */
    Node getParent();

    /**
     * Sets the parent of the node
     * */
    Node setParent(Node node);

    /**
     * @return the value stored in the node
     * */
    Value getValue();

    /**
     * Sets the value of the node
     * */
    Node setValue(Value value);

    /**
     * Compares this node's key to another key. See {@link Key#compare(Key)}
     * */
    int compareKey(Key key);

    /**
     * @return the height of the node. Needed for the AVL tree implementation
     * */
    int getHeight();

    /**
     * Sets the height of the node. Needed for the AVL tree implementation
     * */
    Node setHeight(int height);

}

