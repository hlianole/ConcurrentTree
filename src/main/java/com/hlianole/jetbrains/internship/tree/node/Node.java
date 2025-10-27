package com.hlianole.jetbrains.internship.tree.node;

import com.hlianole.jetbrains.internship.tree.data.Key;
import com.hlianole.jetbrains.internship.tree.data.Value;

public interface Node {

    Node getLeft();

    Node setLeft(Node node);

    Node getRight();

    Node setRight(Node node);

    Node getParent();

    Node setParent(Node node);

    Value getValue();

    Node setValue(Value value);

    int compareKey(Key key);

    int getHeight();

    Node setHeight(int height);

}

