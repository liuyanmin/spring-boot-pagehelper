package com.lym.demo.service;

import com.github.pagehelper.PageInfo;
import com.lym.demo.domain.Country;

/**
 * Created by 刘彦民 on 2019/8/25.
 */
public interface ICountryService {

    PageInfo<Country> getList(int pageNum, int pageSize);
}
