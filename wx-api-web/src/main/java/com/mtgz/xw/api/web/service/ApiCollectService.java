package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.CollectMapper;
import com.mtgz.xw.api.dao.model.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiCollectService {
    @Autowired
    private CollectMapper collectMapper;


    public Collect selectByPrimaryKey(Integer id) {
        return collectMapper.selectByPrimaryKey(id);
    }


    public List<Collect> selectByMap(Map<String, Object> map) {
        return collectMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return collectMapper.countByMap(map);
    }


    public int save(Collect collect) {
        return collectMapper.insertSelective(collect);
    }


    public void update(Collect collect) {
        collectMapper.updateByPrimaryKey(collect);
    }


    public int delete(Integer id) {
        return collectMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        collectMapper.deleteBatch(ids);
    }

}
