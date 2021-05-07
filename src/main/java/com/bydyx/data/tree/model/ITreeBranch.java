package com.bydyx.data.tree.model;

import com.bydyx.collection.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树枝,包含子节点
 *
 * @date 2020/8/12 11:07
 */
public interface ITreeBranch extends ITreeNode {
    List<ITreeBranch> getTreeBranchList();

    void setTreeBranchList(List<ITreeBranch> branchList);

    default void addTreeBranch(List<? extends ITreeBranch> branchList) {
        if (Objects.isNull(branchList)) {
            return;
        }
        List<ITreeBranch> treeBranchList = getTreeBranchList();
        if (Objects.isNull(treeBranchList)) {
            treeBranchList = new ArrayList<>();
            setTreeBranchList(treeBranchList);
        }
        treeBranchList.addAll(branchList);
    }

    default <T extends ITreeBranch> void addTreeBranch(T branch) {
        addTreeBranch(ListUtil.listInit(branch));
    }
}