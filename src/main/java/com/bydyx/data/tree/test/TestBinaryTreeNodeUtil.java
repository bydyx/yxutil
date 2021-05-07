package com.bydyx.data.tree.test;

import com.bydyx.data.tree.model.binaryTree.BinaryTreeNode;
import com.bydyx.data.tree.model.binaryTree.INode;
import com.bydyx.string.PrintUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @date 2020/8/14 11:10
 */
public class TestBinaryTreeNodeUtil {
    public static void main(String[] args) {
        Node node = new Node(13);
        BinaryTreeNode<Node> tree = BinaryTreeNode.of(node);
        tree.addNode(new Node(22));
        tree.addNode(new Node(-91));
        tree.addNode(new Node(119));
        tree.addNode(new Node(-11));
        tree.addNode(new Node(15));
        tree.addNode(new Node(222));
        tree.addNode(new Node(-1));
        tree.addNode(new Node(-11));
        tree.addNode(new Node(-122));
        tree.addNode(new Node(-41));
        tree.addNode(new Node(-61));
        tree.addNode(new Node(-161));
        tree.addNode(new Node(-261));
        tree.addNode(new Node(-361));
        tree.addNode(new Node(-3261));
        tree.addNode(new Node(-3161));
        tree.addNode(new Node(-3361));
        tree.addNode(new Node(-33161));
        tree.addNode(new Node(-3161));
        tree.addNode(new Node(-3161));
        tree.addNode(new Node(-3361));
        tree.addNode(new Node(-361));
        tree.addNode(new Node(-361));
        tree.addNode(new Node(-611));
        tree.addNode(new Node(-2621));
        PrintUtil.printJson(tree);
    }
}

@Data
@Accessors(chain = true)
class Node implements INode {
    Integer id;

    public Node(Integer id) {
        this.id = id;
    }
}