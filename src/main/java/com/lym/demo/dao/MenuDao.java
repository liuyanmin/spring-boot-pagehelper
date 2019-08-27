package com.lym.demo.dao;

import com.lym.demo.domain.Menu;
import com.lym.demo.domain.MenuExample;
import com.lym.demo.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 刘彦民 on 2019/8/27.
 */
@Repository
public class MenuDao {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getList() {
        MenuExample example = new MenuExample();
        return menuMapper.selectByExample(example);
    }
}
