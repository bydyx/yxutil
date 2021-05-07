package com.bydyx.data.tree.test;

import com.bydyx.data.tree.util.TreeBranchUtil;
import com.bydyx.data.tree.model.ITreeBranch;
import com.bydyx.string.PrintUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2020/8/13 10:08
 */
public class TestTreeBranchUtil {
    public static void main(String[] args) {
        List<TreeNode> nodeList = createNodeList();
        List<TreeNode> treeNodes = TreeBranchUtil.treeBranchInit(nodeList);
        PrintUtil.print(treeNodes);
    }

    static List<TreeNode> createNodeList() {
        return new ArrayList<TreeNode>() {{
            add(new TreeNode(1, 0));
            add(new TreeNode(2, 1));
            add(new TreeNode(3, 4));
            add(new TreeNode(11, 0));
            add(new TreeNode(12, 11));
            add(new TreeNode(13, 12));
            add(new TreeNode(132, 13));
            add(new TreeNode(14, 13));
        }};
    }

    @Getter
    @Setter
    @ToString
    private static class TreeNode implements ITreeBranch {
        Integer id;
        Integer parentId;
        List<ITreeBranch> treeBranchList;

        public TreeNode(Integer id, Integer parentId) {
            this.id = id;
            this.parentId = parentId;
        }
    }
}
