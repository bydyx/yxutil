package com.bydyx.data.tree;

import com.bydyx.data.tree.model.ITreeNode;
import com.bydyx.data.tree.util.TreeNodeUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * @date 2020/8/11 16:27
 */
public class TreeUtil {
    public static <T extends ITreeNode> List<T> getChildrenNodeListByParentId(List<T> list, Integer parentId) {
        return getChildrenNodeListByParentId(list, parentId, t -> true);
    }

    /**
     * 返回子级的节点
     *
     * @param predicate 过滤条件
     * @date 2020/8/13 11:05
     */
    public static <T extends ITreeNode> List<T> getChildrenNodeListByParentId(List<T> list, Integer parentId, Predicate<T> predicate) {
        return TreeNodeUtil.getChildrenListByParentId(list, parentId, predicate);
    }

    public static <T extends ITreeNode> List<T> getAllChildrenNodeListByParentId(List<T> list, Integer parentId) {
        return getAllChildrenNodeListByParentId(list, parentId, t -> true);
    }

    /**
     * 返回所有孩子,孙子..节点
     *
     * @param predicate 过滤条件
     */
    public static <T extends ITreeNode> List<T> getAllChildrenNodeListByParentId(List<T> treeList, Integer parentId, Predicate<T> predicate) {
        return TreeNodeUtil.getAllChildrenListByParentId(treeList, parentId, predicate);
    }
}