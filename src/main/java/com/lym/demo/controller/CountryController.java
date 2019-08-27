package com.lym.demo.controller;

import com.github.pagehelper.PageInfo;
import com.lym.demo.domain.Country;
import com.lym.demo.service.ICountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘彦民 on 2019/8/25.
 */
@RestController
@RequestMapping("/country")
@Api(value = "城市管理API接口")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public PageInfo<Country> getList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return countryService.getList(pageNum, pageSize);
    }
}
