package com.bydyx.data.tree.test;

import com.bydyx.data.tree.TreeUtil;
import com.bydyx.data.tree.model.ITreeNode;
import com.bydyx.string.PrintUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020/8/12 8:53
 */
public class TestTreeUtil {
    public static void main(String[] args) {
//       List<ITreeNode> childrenList = TreeUtil.getChildrenListByParentId(createNodeList(), 1);
        List<ITreeNode> childrenList = TreeUtil.getAllChildrenNodeListByParentId(createNodeList(), 0);
    }

    static List<ITreeNode> createNodeList() {
        return new ArrayList<ITreeNode>() {{
            add(new TreeNode(1, 0));
            add(new TreeNode(2, 1));
            add(new TreeNode(3, 4));
            add(new TreeNode(11, 0));
            add(new TreeNode(12, 11));
            add(new TreeNode(13, 0));
            add(new TreeNode(132, 13));
            add(new TreeNode(14, 0));
        }};
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class TreeNode implements ITreeNode {
        Integer id;
        Integer parentId;
    }
}