package com.lym.demo.util;

import com.lym.demo.domain.Menu;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单工具类
 * Created by 刘彦民 on 2019/8/27.
 */
public class MenuUtil {

    /**
     * 获取树形菜单
     * @param menuList 所有菜单list
     * @return
     */
    public static List<Menu> treeMenu(List<Menu> menuList) {
        // 最后的结果
        List<Menu> returnList = new ArrayList<>();

        if (menuList == null || menuList.size() == 0) {
            return returnList;
        }

        // 先找到所有的一级菜单
        for (int i = 0; i < menuList.size(); i++) {
            // 一级菜单没有parentId
            if (StringUtils.isEmpty(menuList.get(i).getParentId())) {
                returnList.add(menuList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menuList) {
            menu.setChildMenus(getChild(menu.getId(), menuList));
        }
        return returnList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param menuList
     *            要查找的列表
     * @return
     */
    private static List<Menu> getChild(String id, List<Menu> menuList) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : menuList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (!StringUtils.isEmpty(menu.getParentId())) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单
            // 递归
            menu.setChildMenus(getChild(menu.getId(), menuList));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
