package com.bydyx.data.tree.model.binaryTree;

import com.bydyx.data.tree.model.ITreeNode;

import java.util.Objects;

public interface IBinaryTreeNode extends ITreeNode {

    IBinaryTreeNode getLeftNode();

    void setLeftNode(IBinaryTreeNode node);

    IBinaryTreeNode getRightNode();

    void setRightNode(IBinaryTreeNode node);

    default boolean isLeafNode() {
        return Objects.isNull(getLeftNode()) && Objects.isNull(getRightNode());
    }

}