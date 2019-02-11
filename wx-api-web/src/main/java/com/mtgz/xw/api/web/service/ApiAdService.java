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
    private AdMapper adMapper;


    public Ad selectByPrimaryKey(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }


    public List<Ad> selectByMap(Map<String, Object> map) {
        return adMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return adMapper.countByMap(map);
    }


    public void save(Ad brand) {
        adMapper.insertSelective(brand);
    }


    public void update(Ad brand) {
        adMapper.updateByPrimaryKeySelective(brand);
    }


    public void delete(Integer id) {
        adMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        adMapper.deleteBatch(ids);
    }

}
