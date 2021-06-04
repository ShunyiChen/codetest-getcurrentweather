package com.shunyi.codetest.city.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shunyi.codetest.city.dao.CityMapper;
import com.shunyi.codetest.city.entity.City;
import org.springframework.stereotype.Service;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 16:38
 */
@Service
public class CityService extends ServiceImpl<CityMapper, City> {
}
