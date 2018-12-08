package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.AdMapper;
import com.mtgz.xw.api.dao.model.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiAdService {
    @Autowired
    private AdMapper adDao;


    public Ad selectByPrimaryKey(Integer id) {
        return adDao.selectByPrimaryKey(id);
    }


    public List<Ad> selectByMap(Map<String, Object> map) {
        return adDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return adDao.countByMap(map);
    }


    public void save(Ad brand) {
        adDao.insert(brand);
    }


    public void update(Ad brand) {
        adDao.updateByPrimaryKey(brand);
    }


    public void delete(Integer id) {
        adDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        adDao.deleteBatch(ids);
    }

}
