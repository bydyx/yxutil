package com.bydyx.data.tree.model.binaryTree;

import com.bydyx.lazy.IfUtil;
import lombok.RequiredArgsConstructor;

import java.util.*;

/**
 * @date 2020/8/17 13:37
 */
@RequiredArgsConstructor(staticName = "of")
public class BinaryTree {
    List<BinaryTreeNode> nodeList = new ArrayList<>();
    BinaryTreeNode root;

    public void addNode(INode obj) {
        addNode(BinaryTreeNode.of(obj));
    }

    public void addNode(BinaryTreeNode node) {
        IfUtil.trueDo(Objects.isNull(root), () -> root = node);
        nodeList.add(node);
        root.addNode(node);
    }

    public BinaryTreeNode getParentNode(BinaryTreeNode parentNode, BinaryTreeNode childNode) {
        if (parentNode.addToLeftOrRight(childNode)) {
            return doGetParentNode(parentNode.getLeftNode(), childNode);
        }
        return doGetParentNode(parentNode.getRightNode(), childNode);
    }

    private BinaryTreeNode doGetParentNode(BinaryTreeNode equalsNode, BinaryTreeNode childNode) {
        return equalsNode.equals(childNode) ? equalsNode : getParentNode(equalsNode, childNode);
    }
}