package com.rany.ops.common.util;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class TreeUtil {

    /**
     * 将list合成树
     *
     * @param list           需要合成树的List
     * @param rootCheck      判断E中为根节点的条件，如：x->x.getPId()==-1L , x->x.getParentId()==null,x->x.getParentMenuId()==0
     * @param parentCheck    判断E中为父节点条件，如：(x,y)->x.getId().equals(y.getPId())
     * @param setSubChildren E中设置下级数据方法，如： Menu::setSubMenus
     * @param <E>            泛型实体对象
     * @return 合成好的树
     */
    public static <E> List<E> makeTree(List<E> list, Predicate<E> rootCheck, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> setSubChildren) {
        return list.stream().filter(rootCheck).peek(x -> setSubChildren.accept(x, makeChildren(x, list, parentCheck, setSubChildren))).collect(Collectors.toList());
    }

    private static <E> List<E> makeChildren(E parent, List<E> allData, BiFunction<E, E, Boolean> parentCheck, BiConsumer<E, List<E>> children) {
        return allData.stream().filter(x -> parentCheck.apply(parent, x)).peek(x -> children.accept(x, makeChildren(x, allData, parentCheck, children))).collect(Collectors.toList());
    }


    /**
     * 将树打平成tree
     *
     * @param tree           需要打平的树
     * @param getSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param setSubChildren 将下级数据置空方法，如： x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     * @return 打平后的数据
     */
    public static <E> List<E> flat(List<E> tree, Function<E, List<E>> getSubChildren, Consumer<E> setSubChildren) {
        List<E> res = new ArrayList<>();
        forPostOrder(tree, item -> {
            setSubChildren.accept(item);
            res.add(item);
        }, getSubChildren);
        return res;
    }


    /**
     * 前序遍历
     *
     * @param tree           需要遍历的树
     * @param consumer       遍历后对单个元素的处理方法，如：x-> System.out.println(x)、 System.out::println打印元素
     * @param setSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     */
    public static <E> void forPreOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> setSubChildren) {
        for (E l : tree) {
            consumer.accept(l);
            List<E> es = setSubChildren.apply(l);
            if (es != null && !es.isEmpty()) {
                forPreOrder(es, consumer, setSubChildren);
            }
        }
    }


    /**
     * 层序遍历
     *
     * @param tree           需要遍历的树
     * @param consumer       遍历后对单个元素的处理方法，如：x-> System.out.println(x)、 System.out::println打印元素
     * @param setSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     */
    public static <E> void forLevelOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> setSubChildren) {
        Queue<E> queue = new LinkedList<>(tree);
        while (!queue.isEmpty()) {
            E item = queue.poll();
            consumer.accept(item);
            List<E> childList = setSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                queue.addAll(childList);
            }
        }
    }


    /**
     * 后序遍历
     *
     * @param tree           需要遍历的树
     * @param consumer       遍历后对单个元素的处理方法，如：x-> System.out.println(x)、 System.out::println打印元素
     * @param setSubChildren 设置下级数据方法，如： Menu::getSubMenus,x->x.setSubMenus(null)
     * @param <E>            泛型实体对象
     */
    public static <E> void forPostOrder(List<E> tree, Consumer<E> consumer, Function<E, List<E>> setSubChildren) {
        for (E item : tree) {
            List<E> childList = setSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                forPostOrder(childList, consumer, setSubChildren);
            }
            consumer.accept(item);
        }
    }

    /**
     * 对树所有子节点按comparator排序
     *
     * @param tree        需要排序的树
     * @param comparator  排序规则Comparator，如：Comparator.comparing(MenuVo::getRank)按Rank正序 ,(x,y)->y.getRank().compareTo(x.getRank())，按Rank倒序
     * @param getChildren 获取下级数据方法，如：MenuVo::getSubMenus
     * @param <E>         泛型实体对象
     * @return 排序好的树
     */
    public static <E> List<E> sort(List<E> tree, Comparator<? super E> comparator, Function<E, List<E>> getChildren) {
        for (E item : tree) {
            List<E> childList = getChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                sort(childList, comparator, getChildren);
            }
        }
        tree.sort(comparator);
        return tree;
    }

    @Data
    @Builder
    public static class GroupVO {
        private String groupId;
        private String parentGroupId;
        private String groupName;
        private List<GroupVO> subGroups = new ArrayList<>();
    }


    public static void main(String[] args) {
        List<GroupVO> groupList = new ArrayList<>();
        groupList.add(new GroupVO.GroupVOBuilder().groupId("1").groupName("一级部门").parentGroupId(null).build());
        groupList.add(new GroupVO.GroupVOBuilder().groupId("2").groupName("二级部门1").parentGroupId("1").build());
        groupList.add(new GroupVO.GroupVOBuilder().groupId("3").groupName("三级部门1").parentGroupId("2").build());
        groupList.add(new GroupVO.GroupVOBuilder().groupId("4").groupName("二级部门2").parentGroupId("1").build());
        groupList.add(new GroupVO.GroupVOBuilder().groupId("5").groupName("三级部门2").parentGroupId("4").build());
        List<GroupVO> groupTree = TreeUtil.makeTree(groupList, x -> x.getParentGroupId() == null, (x, y) -> x.getGroupId().equals(y.getParentGroupId()), GroupVO::setSubGroups);
        List<GroupVO> flatGroups = TreeUtil.flat(groupTree, GroupVO::getSubGroups, p -> p.setSubGroups(null));
        System.out.println(JSON.toJSONString(groupTree));
    }
}