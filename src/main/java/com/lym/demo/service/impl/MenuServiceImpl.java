package com.lym.demo.service.impl;

import com.lym.demo.dao.MenuDao;
import com.lym.demo.domain.Menu;
import com.lym.demo.service.IMenuService;
import com.lym.demo.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘彦民 on 2019/8/27.
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Object getList() {
        List<Menu> menuList = menuDao.getList();

        List<Menu> treeMenus = MenuUtil.treeMenu(menuList);

        return treeMenus;
    }

    @Override
    public Object getUserMenuList() {
        // 为了测试这里写死具有的子菜单ID，实际应该是从数据库中读取出来
        List<String> hasMenuIds = new ArrayList<>();
        hasMenuIds.add("子菜单ID");

        List<Menu> childMenus = menuDao.getByMenuIds(hasMenuIds);

        // 所有菜单列表
        List<Menu> menuList = menuDao.getList();

        // 获取菜单树
        List<Menu> menus =MenuUtil.getMenuList(menuList, childMenus);
        return menus;
    }
}
