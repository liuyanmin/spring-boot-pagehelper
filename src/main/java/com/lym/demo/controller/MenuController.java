package com.lym.demo.controller;

import com.lym.demo.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘彦民 on 2019/8/27.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/list")
    public Object getList() {
        return menuService.getList();
    }
}
