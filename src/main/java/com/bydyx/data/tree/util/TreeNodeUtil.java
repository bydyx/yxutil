package com.bydyx.data.tree.util;

import com.bydyx.collection.ListUtil;
import com.bydyx.collection.StreamUtil;
import com.bydyx.data.tree.model.ITreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 节点结构的树
 *
 * @date 2020/8/12 11:09
 */
public class TreeNodeUtil {
    /**
     * 返回子级的节点
     *
     * @date 2020/8/11 17:26
     */
    public static <T extends ITreeNode> List<T> getChildrenListByParentId(List<T> tree, Integer parentId, Predicate<T> predicate) {
        Map<Integer, List<T>> parentIdMap = StreamUtil.groupBy(tree, T::getParentId);
        List<T> result = parentIdMap.get(parentId);
        if (Objects.isNull(result)) {
            return new ArrayList<>();
        }
        return StreamUtil.filter(result, predicate);
    }

    /**
     * 返回所有孩子(孙子节点)
     *
     * @date 2020/8/11 17:26
     */
    public static <T extends ITreeNode> List<T> getAllChildrenListByParentId(List<T> nodeList, Integer parentId, Predicate<T> predicate) {
        List<T> resultList = new ArrayList<>();
        Map<Integer, List<T>> parentIdMap = nodeList.stream()
                                                    .filter(predicate)
                                                    .collect(Collectors.groupingBy(T::getParentId));
        doGetChildren(parentIdMap, resultList, parentId);
        return resultList;
    }

    private static <T extends ITreeNode> void doGetChildren(Map<Integer, List<T>> parentIdMap, List<T> resultList, Integer parentId) {
        List<T> childList = parentIdMap.get(parentId);
        parentIdMap.remove(parentId);
        if (Objects.nonNull(childList)) {
            resultList.addAll(childList);
            childList.stream()
                     .map(T::getId)
                     .map(parentIdMap::get)
                     .filter(Objects::nonNull)
                     .reduce(ListUtil::merge)
                     .ifPresent(list -> doGetChildrenList(parentIdMap, resultList, list));
        }
    }

    private static <T extends ITreeNode> void doGetChildrenList(Map<Integer, List<T>> parentIdMap, List<T> resultList, List<T> list) {
        resultList.addAll(list);
        list.stream()
            .map(T::getId)
            .forEach(id -> doGetChildren(parentIdMap, resultList, id));
    }
}
