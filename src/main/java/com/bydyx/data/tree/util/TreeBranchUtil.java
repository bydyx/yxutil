package com.bydyx.data.tree.util;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;
import com.bydyx.data.tree.model.ITreeBranch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 枝干结构的数
 * @date 2020/8/12 11:13
 */
public class TreeBranchUtil {

    /**
     * list转tree
     * 元素父级id不在list中会被当做根节点
     *
     * @param sourceList 需要初始化的list
     * @date 2020/8/12 11:19
     */
    public static <T extends ITreeBranch> List<T> treeBranchInit(List<T> sourceList) {
        Map<Integer, List<T>> parentIdMap = StreamUtil.groupBy(sourceList, T::getParentId);
        Map<Integer, T> idMap = StreamUtil.listToMap(sourceList, T::getId);
        return parentIdMap.keySet()
                          .stream()
                          .filter(key -> !idMap.containsKey(key))
                          .map(parentIdMap::get)
                          .reduce(ListUtil::merge)
                          .orElse(new ArrayList<>())
                          .stream()
                          .peek(t -> branchInit(parentIdMap, t))
                          .collect(Collectors.toList());
    }

    private static <T extends ITreeBranch> void branchInit(Map<Integer, List<T>> parentIdMap, T root) {
        List<T> nextNodeList = parentIdMap.get(root.getId());
        if (Objects.nonNull(nextNodeList)) {
            root.addTreeBranch(nextNodeList);
            parentIdMap.remove(root.getId());

            nextNodeList.forEach(t -> branchInit(parentIdMap, t));
        }
    }
}