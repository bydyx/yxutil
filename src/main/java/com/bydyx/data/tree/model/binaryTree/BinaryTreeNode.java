package com.bydyx.data.tree.model.binaryTree;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;


/**
 * @date 2020/8/13 13:07
 */
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
public class BinaryTreeNode<T extends INode> {
    BinaryTreeNode<T> leftNode;
    BinaryTreeNode<T> rightNode;

    @JSONField(serialize = false)
    T data;

    public Integer getId() {
        return data.getId();
    }

    private BinaryTreeNode(T data) {
        this.data = data;
    }

    public static <T extends INode> BinaryTreeNode<T> of(T t) {
        return new BinaryTreeNode<>(t);
    }

    public void addNode(T node) {
        addNode(BinaryTreeNode.of(node));
    }

    public void addNode(BinaryTreeNode<T> node) {
        if (addToLeftOrRight(node)) {
            if (Objects.nonNull(leftNode)) {
                leftNode.addNode(node);
            } else {
                this.leftNode = node;
            }
        } else {
            if (Objects.nonNull(rightNode)) {
                rightNode.addNode(node);
            } else {
                this.rightNode = node;
            }
        }
        if (BinaryTreeHelp.isCorrect(this)) {
            rotateBalanced();
        }
    }

    /**
     * true->左
     * false->右
     *
     * @date 2020/8/14 10:48
     */
    public boolean addToLeftOrRight(BinaryTreeNode<T> node) {
        return getId().compareTo(node.getId()) >= 0;
    }

    public void rotateBalanced() {
        switch (BinaryTreeHelp.getBalancedType(this)) {
            case LEFT_LEFT:
                rightRotate();
                break;
            case RIGHT_RIGHT:
                leftRotate();
                break;
            case LEFT_RIGHT:
                leftRotate();
                rightRotate();
                break;
            case RIGHT_LEFT:
                rightRotate();
                leftRotate();
                break;
            case CORRECT:
        }
    }

    private void rightRotate() {
        this.setRightNode(BinaryTreeHelp.cloneBinaryTreeNode(this));
        rightNode.setLeftNode(leftNode.getRightNode());

        this.setData(leftNode.getData());
        this.setLeftNode(leftNode.getLeftNode());
    }

    private void leftRotate() {
        this.setLeftNode(BinaryTreeHelp.cloneBinaryTreeNode(this));
        leftNode.setRightNode(rightNode.getLeftNode());

        this.setData(rightNode.getData());
        this.setRightNode(rightNode.getRightNode());
    }

}