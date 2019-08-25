package com.lym.demo.service.impl;

import com.github.pagehelper.PageInfo;
import com.lym.demo.dao.CountryDao;
import com.lym.demo.domain.Country;
import com.lym.demo.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘彦民 on 2019/8/25.
 */
@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public PageInfo<Country> getList(int pageNum, int pageSize) {
        List<Country> countryList = countryDao.getList(pageNum, pageSize);
        return new PageInfo<>(countryList);
    }
}
