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
    private CollectMapper collectDao;


    public Collect selectByPrimaryKey(Integer id) {
        return collectDao.selectByPrimaryKey(id);
    }


    public List<Collect> selectByMap(Map<String, Object> map) {
        return collectDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return collectDao.countByMap(map);
    }


    public int save(Collect collect) {
        return collectDao.insertSelective(collect);
    }


    public void update(Collect collect) {
        collectDao.updateByPrimaryKey(collect);
    }


    public int delete(Integer id) {
        return collectDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        collectDao.deleteBatch(ids);
    }

}
