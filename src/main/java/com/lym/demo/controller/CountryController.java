package com.lym.demo.controller;

import com.github.pagehelper.PageInfo;
import com.lym.demo.domain.Country;
import com.lym.demo.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘彦民 on 2019/8/25.
 */
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @RequestMapping("/list")
    public PageInfo<Country> getList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return countryService.getList(pageNum, pageSize);
    }
}
