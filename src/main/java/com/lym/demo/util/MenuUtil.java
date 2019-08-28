package com.lym.demo.util;

import com.lym.demo.domain.Menu;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单工具类
 * 1、支持返回一整棵完整的菜单树（包含父子菜单的列表）
 * 2、支持根据具有的子菜单，返回一个菜单树（包含父子菜单的列表）
 * 
 * Created by 刘彦民 on 2019/8/27.
 */
public class MenuUtil {

    /**
     * 根据具有的子菜单权限来构建菜单树
     * @param menuList
     * @param childMenus
     * @return
     */
    public static List<Menu> getMenuList(List<Menu> menuList, List<Menu> childMenus) {
        if (menuList == null || menuList.size() == 0 || childMenus == null || childMenus.size() == 0) {
            return null;
        }

        Map<String, Menu> menuMap = new LinkedHashMap<>();
        for (Menu childMenu : childMenus) {
            // 获取当前节点的根节点
            Menu root = getRoot(menuList, childMenu);
            menuTreeR(menuList, root, childMenu);
            menuMap.put(root.getId(), root);
        }

        return (List<Menu>) menuMap.values();
    }

    /**
     * 获取当前节点树(从子节点到根节点的一棵树)
     * @param menuList 所有菜单list
     * @param root 当前节点的根节点
     * @param currMenu 当前节点(最后一层菜单，包含url的)
     * @return
     */
    private static Menu menuTreeR(List<Menu> menuList, Menu root, Menu currMenu) {
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            if (StringUtils.isEmpty(menu.getParentId()) && menu.getParentId().equals(root.getId())) {
                List<Menu> children = root.getChildMenus();
                if (children == null) {
                    children = new ArrayList<>();
                }
                children.add(menu);
                root.setChildMenus(children);

                if (menu.getId().equals(currMenu.getId())) {
                    return root;
                }
                menuTreeR(menuList, menu, currMenu);
            }
        }
        return root;
    }

    /**
     * 获取当前菜单的根节点
     * @param menuList 所有菜单list
     * @param currMenu 当前菜单
     * @return 返回根节点
     */
    public static Menu getRoot(List<Menu> menuList, Menu currMenu) {
        Menu root = currMenu;
        while(true) {
            if (StringUtils.isEmpty(root.getParentId())) {
                return root;
            }
            for (Menu menu : menuList) {
                if (menu.getId().equals(root.getParentId())) {
                    root = menu;
                    break;
                }
            }
        }
    }

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
