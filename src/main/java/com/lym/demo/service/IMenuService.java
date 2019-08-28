package com.lym.demo.service;

/**
 * Created by 刘彦民 on 2019/8/27.
 */
public interface IMenuService {

    /**
     * 获取所有菜单树
     * @return
     */
    Object getList();

    /**
     * 获取用户具有的菜单树
     * @return
     */
    Object getUserMenuList();

}
