package com.lym.demo.dao;

import com.github.pagehelper.PageHelper;
import com.lym.demo.domain.Country;
import com.lym.demo.domain.CountryExample;
import com.lym.demo.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 刘彦民 on 2019/8/25.
 */
@Repository
public class CountryDao {

    @Autowired
    private CountryMapper countryMapper;

    public List<Country> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Country> list = countryMapper.selectCountryList();
        return list;
    }
}
