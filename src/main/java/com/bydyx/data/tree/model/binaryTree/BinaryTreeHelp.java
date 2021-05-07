package com.bydyx.data.tree.model.binaryTree;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

/**
 * @date 2020/8/13 13:07
 */
public class BinaryTreeHelp {
    public static int getHeight(BinaryTreeNode node) {
        return NumberUtils.max(leftHeight(node), rightHeight(node)) + 1;
    }

    private static int rightHeight(BinaryTreeNode node) {
        if (Objects.isNull(node.rightNode)) {
            return 0;
        }
        return BinaryTreeHelp.getHeight(node.rightNode);
    }

    private static int leftHeight(BinaryTreeNode node) {
        if (Objects.isNull(node.leftNode)) {
            return 0;
        }
        return BinaryTreeHelp.getHeight(node.leftNode);
    }

    public static int balanceFactor(BinaryTreeNode node) {
        return leftHeight(node) - rightHeight(node);
    }

    public static BinaryTreeBalancedType getBalancedType(BinaryTreeNode node) {
        //偏左
        if (BinaryTreeHelp.balanceFactor(node) > 1) {
            switch (BinaryTreeHelp.balanceFactor(node.leftNode)) {
                case -1:
                    return BinaryTreeBalancedType.LEFT_RIGHT;
                case 0:
                case 1:
                    return BinaryTreeBalancedType.LEFT_LEFT;
            }
        }
        if (BinaryTreeHelp.balanceFactor(node) < -1) {
            switch (BinaryTreeHelp.balanceFactor(node.rightNode)) {
                case -1:
                    return BinaryTreeBalancedType.RIGHT_RIGHT;
                case 0:
                case 1:
                    return BinaryTreeBalancedType.RIGHT_LEFT;
            }
        }
        return BinaryTreeBalancedType.CORRECT;
    }

    public static <T extends INode> boolean isCorrect(BinaryTreeNode<T> node) {
        return getBalancedType(node).equals(BinaryTreeBalancedType.CORRECT);
    }


    public static <T extends INode> BinaryTreeNode<T> cloneBinaryTreeNode(BinaryTreeNode<T> node) {
        return BinaryTreeNode.of(node.data)
                             .setLeftNode(node.leftNode)
                             .setRightNode(node.rightNode);
    }

}