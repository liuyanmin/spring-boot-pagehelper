package com.lym.demo.service.impl;

import com.lym.demo.dao.MenuDao;
import com.lym.demo.domain.Menu;
import com.lym.demo.service.IMenuService;
import com.lym.demo.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
